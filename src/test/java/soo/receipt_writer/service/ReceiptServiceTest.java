package soo.receipt_writer.service;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import soo.receipt_writer.domain.Receipt;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReceiptServiceTest {

    @Autowired
    private SqlSession session;

    @Test
    void addReceipt() {
        Receipt receipt = new Receipt("2024-01-24", "10000", "서울시 강남구 역삼동");
        int insertCnt = session.insert("DB_RECEIPT.insertOne", receipt);
        assertEquals(1, insertCnt);
    }
}