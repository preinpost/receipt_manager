package soo.receipt_writer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import soo.receipt_writer.domain.Receipt;

@SpringBootTest
class ReceiptServiceTest {

    @Autowired
    private ReceiptService receiptService;

    @Test
    void addReceipt() {
        Receipt receipt = new Receipt("2024-01-24", "10000", "서울시 강남구 역삼동");
        receiptService.addReceipt(receipt);
    }
}