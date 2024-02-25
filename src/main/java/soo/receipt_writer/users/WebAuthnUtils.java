package soo.receipt_writer.users;

import com.webauthn4j.authenticator.Authenticator;
import com.webauthn4j.data.AuthenticationParameters;
import com.webauthn4j.data.AuthenticationRequest;
import com.webauthn4j.data.RegistrationParameters;
import com.webauthn4j.data.RegistrationRequest;
import com.webauthn4j.server.ServerProperty;
import com.webauthn4j.util.Base64UrlUtil;

import java.io.*;
import java.util.Base64;

public class WebAuthnUtils {

    static public String serializeAuthenticatorToBase64(Authenticator authenticator) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(authenticator);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize Authenticator object", e);
        }
    }

    static public RegistrationRequest createRegistrationRequest(String clientDataBase64,
                                                  String attestationObjectBase64,
                                                  String clientExtensionsJSON) {

        byte[] clientDataBytes = Base64UrlUtil.decode(clientDataBase64);
        byte[] attestationObjectBytes = Base64UrlUtil.decode(attestationObjectBase64);

        return new RegistrationRequest(
                attestationObjectBytes,
                clientDataBytes,
                clientExtensionsJSON,
                null
        );
    }

    static public RegistrationParameters createRegistrationParameters(ServerProperty serverProperty) {
        return new RegistrationParameters(
                serverProperty,
                null,
                false,
                false
        );
    }

    static public Authenticator base64ToAuthenticator(String base64Authenticator) {
        byte[] data = Base64.getDecoder().decode(base64Authenticator);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (Authenticator) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to convert base64 to Authenticator", e);
        }
    }

    static public AuthenticationRequest createAuthenticationRequest(
            String credentialId,
            String userHandle,
            String authenticatorData,
            String clientDataJSON,
            String clientExtensionsJSON,
            String signature
    ) {

        byte[] credentialIdBytes = Base64UrlUtil.decode(credentialId);
        byte[] userHandleBytes = Base64UrlUtil.decode(userHandle);
        byte[] clientDataBytes = Base64UrlUtil.decode(clientDataJSON);
        byte[] authenticatorDataBytes = Base64UrlUtil.decode(authenticatorData);
        byte[] signatureBytes = Base64UrlUtil.decode(signature);

        return new AuthenticationRequest(
                credentialIdBytes,
                userHandleBytes,
                authenticatorDataBytes,
                clientDataBytes,
                clientExtensionsJSON,
                signatureBytes
        );
    }

    static public AuthenticationParameters createAuthenticationParameters(
            ServerProperty serverProperty,
            Authenticator authenticator
    ) {
        return new AuthenticationParameters(
                serverProperty,
                authenticator,
                null,
                true,
                true
        );
    }

}
