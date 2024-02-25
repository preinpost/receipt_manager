package soo.receipt_writer.users.dto.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class AuthenticationResponseJson implements Serializable {
    final public String credentialId;
    final public String clientDataJSON;
    final public String authenticatorData;
    final public String signature;
    final public String userHandle;
}
