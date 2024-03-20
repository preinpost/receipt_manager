package soo.receipt_writer.users.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
class WebAuthnServiceTest {

    @Test
    void localDateTest() {
        String string = LocalDate.now(ZoneId.of("Asia/Seoul")).toString().replaceAll("-", "");
        System.out.println("string = " + string);
    }
}