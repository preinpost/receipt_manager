package soo.receipt_writer;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisConnectionTests {

    @Autowired
    private SqlSession session;

    @Test
    public void testConnection() {
        Object result = session.selectOne("TB_RECEIPT.selectOne");
        System.out.println(result);
    }
}
