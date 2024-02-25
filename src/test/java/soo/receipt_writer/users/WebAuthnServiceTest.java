package soo.receipt_writer.users;

import com.github.f4b6a3.uuid.UuidCreator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WebAuthnServiceTest {

    @Test
    void uuidv7Test() {
        UUID uuid = UuidCreator.getTimeOrderedEpoch();
        System.out.println("uuid = " + uuid.toString());
        System.out.println("uuid.toString().length() = " + uuid.toString().length());
    }

}