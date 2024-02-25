package soo.receipt_writer.users;

import com.webauthn4j.data.client.Origin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class WebAuthnProperty {

    private final Origin origin;
    private final String rp;
    private final String rpName;

}
