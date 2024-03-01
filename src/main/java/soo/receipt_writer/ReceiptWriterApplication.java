package soo.receipt_writer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "soo")
public class ReceiptWriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiptWriterApplication.class, args);
    }
}
