package soo.receipt_writer.users;

import com.webauthn4j.authenticator.Authenticator;
import com.webauthn4j.data.client.challenge.DefaultChallenge;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class WebauthnSession {
    private final UUID uuid;
    private final String userId;
    private final DefaultChallenge challenge;

    @Nullable
    private Authenticator authenticator;
}
