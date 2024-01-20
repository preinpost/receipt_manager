package soo.receipt_writer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({soo.h2Dev.H2Config.class})
@SpringBootApplication
public class ReceiptWriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiptWriterApplication.class, args);
    }

}
