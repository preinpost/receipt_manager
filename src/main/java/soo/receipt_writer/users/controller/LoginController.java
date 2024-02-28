package soo.receipt_writer.users.controller;

import com.webauthn4j.data.PublicKeyCredentialRequestOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import soo.receipt_writer.commons.SuccessResponse;
import soo.receipt_writer.users.dto.authentication.AuthenticationInfo;
import soo.receipt_writer.users.dto.authentication.AuthenticationResponseJson;
import soo.receipt_writer.users.dto.registration.PublicKey;
import soo.receipt_writer.users.dto.registration.RegistrationInfo;
import soo.receipt_writer.users.dto.registration.RegistrationResponseJSON;
import soo.receipt_writer.users.service.WebAuthnService;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final WebAuthnService webAuthnService;

    @GetMapping("register_challenge")
    public SuccessResponse<PublicKey> getChallenge(RegistrationInfo info) {
        return new SuccessResponse<>(webAuthnService.getChallenge(info));
    }

    @PostMapping("register_passkey")
    public SuccessResponse<Void> registerUser(@RequestBody RegistrationResponseJSON request) {
        webAuthnService.registerPasskey(request);
        return SuccessResponse.emptyResponse();
    }

    @GetMapping("authentication_challenge")
    public SuccessResponse<PublicKeyCredentialRequestOptions> getLoginChallenge(AuthenticationInfo info) {
        return new SuccessResponse<>(webAuthnService.getAuthenticationChallenge(info));
    }

    @PostMapping("authentication_passkey")
    public SuccessResponse<Void> authenticate(@RequestBody AuthenticationResponseJson request) {
        webAuthnService.authenticationPasskey(request);
        return SuccessResponse.emptyResponse();
    }
}
