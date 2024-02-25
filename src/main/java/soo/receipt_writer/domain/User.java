package soo.receipt_writer.domain;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class User {

    private String uid;
    private String userId;

    @Nullable
    private String password;

    @Nullable
    private String passkey;

    static public User addPasskeyUser(String uid, String userId, String passkey) {
        return new User(uid, userId, null, passkey);
    }
}
