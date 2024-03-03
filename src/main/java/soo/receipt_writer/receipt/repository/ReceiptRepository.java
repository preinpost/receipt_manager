package soo.receipt_writer.receipt.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReceiptRepository {

    private final SqlSession session;

    public int insertOne(Receipt receipt) {
        return session.insert("TB_RECEIPT.insertOne", receipt);
    }

    public long getMaxSeq(Receipt receipt) {
        return session.selectOne("TB_RECEIPT.getMaxSeq", receipt);
    }

    public Receipt selectOne() {
        return session.selectOne("TB_RECEIPT.selectOne");
    }

    public List<Receipt> selectAll(String userId) {
        return session.selectList("TB_RECEIPT.selectAll", userId);
    }
}
