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
        return session.insert("TB_RECEIPT.insertOne", receipt);
    }

    public Receipt selectOne() {
        return session.selectOne("TB_RECEIPT.selectOne");
    }

    public List<Receipt> selectAll() {
        return session.selectList("TB_RECEIPT.selectAll");
    }
}
