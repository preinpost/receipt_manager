package soo.receipt_writer.users.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.webauthn4j.WebAuthnManager;
import com.webauthn4j.authenticator.Authenticator;
import com.webauthn4j.authenticator.AuthenticatorImpl;
import com.webauthn4j.data.*;
import com.webauthn4j.data.attestation.statement.COSEAlgorithmIdentifier;
import com.webauthn4j.data.client.challenge.DefaultChallenge;
import com.webauthn4j.data.extension.client.AuthenticationExtensionsClientInputs;
import com.webauthn4j.server.ServerProperty;
import com.webauthn4j.util.Base64UrlUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import soo.receipt_writer.users.repository.User;
import soo.receipt_writer.users.LoginSession;
import soo.receipt_writer.users.repository.UserRepository;
import soo.receipt_writer.users.WebAuthnProperty;
import soo.receipt_writer.users.WebauthnSession;
import soo.receipt_writer.users.dto.authentication.AuthenticationInfo;
import soo.receipt_writer.users.dto.authentication.AuthenticationResponseJson;
import soo.receipt_writer.users.dto.registration.PublicKey;
import soo.receipt_writer.users.dto.registration.RegistrationInfo;
import soo.receipt_writer.users.dto.registration.RegistrationResponseJSON;
import soo.receipt_writer.users.repository.dao.RegisterUserDAO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static soo.receipt_writer.users.WebAuthnUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebAuthnService {

    private final WebAuthnManager webAuthnManager;
    private final HttpSession session;
    private final WebAuthnProperty webAuthnProperty;
    private final UserRepository userRepository;

    public PublicKey getChallenge(RegistrationInfo registerInfo) {

        User existCheckUser = userRepository.selectOneById(registerInfo.getId());

        if (existCheckUser != null) {
            throw new RuntimeException("이미 등록된 사용자입니다.");
        }

        UUID uuid = UuidCreator.getTimeOrderedEpoch();

        byte[] userBase64UrlEncoded = Base64UrlUtil.encode(uuid.toString().getBytes());

        PublicKeyCredentialRpEntity rp = new PublicKeyCredentialRpEntity(webAuthnProperty.getRp(), webAuthnProperty.getRpName());
        PublicKeyCredentialUserEntity user = new PublicKeyCredentialUserEntity(userBase64UrlEncoded, registerInfo.getId(), registerInfo.getId());
        DefaultChallenge challenge = new DefaultChallenge();

        List<PublicKeyCredentialParameters> pubKeyCredParams = List.of(
                new PublicKeyCredentialParameters(PublicKeyCredentialType.PUBLIC_KEY, COSEAlgorithmIdentifier.ES256),
                new PublicKeyCredentialParameters(PublicKeyCredentialType.PUBLIC_KEY, COSEAlgorithmIdentifier.RS256)
        );

        Long timeout = 60000L;
        AttestationConveyancePreference attestation = AttestationConveyancePreference.DIRECT;
        AuthenticatorSelectionCriteria authenticatorSelection = new AuthenticatorSelectionCriteria(AuthenticatorAttachment.PLATFORM, false, UserVerificationRequirement.PREFERRED);
        AuthenticationExtensionsClientInputs extension = new AuthenticationExtensionsClientInputs.BuilderForRegistration().setUvm(true).setCredProps(true).build();

        PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions = new PublicKeyCredentialCreationOptions(rp, user, challenge, pubKeyCredParams, timeout, null, authenticatorSelection, attestation, extension);
        session.setAttribute("webauthnSession", new WebauthnSession(uuid, registerInfo.getId(), challenge));

        return new PublicKey(publicKeyCredentialCreationOptions);
    }

    public void registerPasskey(@RequestBody RegistrationResponseJSON request) {

        RegistrationRequest webAuthnRegistrationRequest =
                createRegistrationRequest(request.getClientDataJSON(), request.getAttestationObject(), request.getClientExtensions());

        RegistrationParameters webAuthnRegistrationParameters =
                createRegistrationParameters(
                        new ServerProperty(
                                webAuthnProperty.getOrigin(),
                                webAuthnProperty.getRp(),
                                ((WebauthnSession) session.getAttribute("webauthnSession")).getChallenge(),
                                null
                        )
                );

        RegistrationData response = webAuthnManager.validate(webAuthnRegistrationRequest, webAuthnRegistrationParameters);

        Authenticator authenticator = new AuthenticatorImpl(
                response.getAttestationObject().getAuthenticatorData().getAttestedCredentialData(),
                response.getAttestationObject().getAttestationStatement(),
                response.getAttestationObject().getAuthenticatorData().getSignCount()
        );

        String passkeyBase64 = serializeAuthenticatorToBase64(authenticator);

        log.debug("passkeyBase64.length() = {}", passkeyBase64.length());

        userRepository.insertOne(
                new RegisterUserDAO(
                        ((WebauthnSession) session.getAttribute("webauthnSession")).getUuid().toString(),
                        ((WebauthnSession) session.getAttribute("webauthnSession")).getUserId(),
                        passkeyBase64,
                        LocalDate.now(ZoneId.of("Asia/Seoul")).toString().replaceAll("-", "")
                )
        );
    }

    public PublicKeyCredentialRequestOptions getAuthenticationChallenge(AuthenticationInfo info) {
        User userData = userRepository.selectOneById(info.getId());

        if (userData == null) {
            throw new RuntimeException("사용자가 없습니다.");
        }

        PublicKeyCredentialRpEntity rp = new PublicKeyCredentialRpEntity(webAuthnProperty.getRp(), webAuthnProperty.getRpName());
        DefaultChallenge challenge = new DefaultChallenge();

        Authenticator authenticator = base64ToAuthenticator(userData.passkey());
        PublicKeyCredentialDescriptor publicKeyCredentialDescriptor = new PublicKeyCredentialDescriptor(PublicKeyCredentialType.PUBLIC_KEY, authenticator.getAttestedCredentialData().getCredentialId(), authenticator.getTransports());
        UserVerificationRequirement userVerificationRequirement = UserVerificationRequirement.PREFERRED;

        session.setAttribute("webauthnSession", new WebauthnSession(UUID.fromString(userData.uid()), info.getId(), challenge, authenticator));

        return new PublicKeyCredentialRequestOptions(challenge, 60000L, rp.getId(), List.of(publicKeyCredentialDescriptor), userVerificationRequirement, null);
    }

    public void authenticationPasskey(AuthenticationResponseJson request) {
        AuthenticationRequest authenticationRequest = createAuthenticationRequest(
                request.getCredentialId(),
                request.getUserHandle(),
                request.getAuthenticatorData(),
                request.getClientDataJSON(),
                null,
                request.getSignature()
        );

        AuthenticationParameters authenticationParameters = createAuthenticationParameters(
                new ServerProperty(
                        webAuthnProperty.getOrigin(),
                        webAuthnProperty.getRp(),
                        ((WebauthnSession) session.getAttribute("webauthnSession")).getChallenge(),
                        null
                ),
                ((WebauthnSession) session.getAttribute("webauthnSession")).getAuthenticator()
        );

        AuthenticationData response = webAuthnManager.validate(authenticationRequest, authenticationParameters);

        String userId = ((WebauthnSession) session.getAttribute("webauthnSession")).getUserId();
        User user = userRepository.selectOneById(userId);

        session.setAttribute("loginSession", new LoginSession(user.userId(), user.joinDate()));
    }
}
