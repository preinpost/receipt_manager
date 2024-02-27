package soo.receipt_writer.users;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginSession {

    private String userId;

    public LoginSession(String userId) {
        this.userId = userId;
    }
}
