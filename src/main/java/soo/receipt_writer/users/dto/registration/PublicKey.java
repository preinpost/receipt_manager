package soo.receipt_writer.users.dto.registration;

import com.webauthn4j.data.PublicKeyCredentialCreationOptions;

import java.io.Serializable;

public class PublicKey implements Serializable {

    final public PublicKeyCredentialCreationOptions publicKey;

    public PublicKey(PublicKeyCredentialCreationOptions publicKey) {
        this.publicKey = publicKey;
    }
}
