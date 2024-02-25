package soo.receipt_writer.users.dto.registration;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter @ToString
public class RegistrationResponseJSON implements Serializable {

    final public String clientDataJSON;
    final public String attestationObject;
    final public String clientExtensions;

    public RegistrationResponseJSON(String clientDataJSON, String attestationObject, String clientExtensions) {
        this.clientDataJSON = clientDataJSON;
        this.attestationObject = attestationObject;
        this.clientExtensions = clientExtensions;
    }
}