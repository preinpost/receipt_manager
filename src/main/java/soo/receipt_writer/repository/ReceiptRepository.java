package soo.receipt_writer.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import soo.receipt_writer.domain.Receipt;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReceiptRepository {

    private final SqlSession session;

    public int insertOne(Receipt receipt) {
        return session.insert("DB_RECEIPT.insertOne", receipt);
    }

    public Receipt selectOne() {
        return session.selectOne("DB_RECEIPT.selectOne");
    }

    public List<Receipt> selectAll() {
        return session.selectList("DB_RECEIPT.selectAll");
    }
}
