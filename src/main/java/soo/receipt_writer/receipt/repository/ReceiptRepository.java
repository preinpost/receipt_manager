package soo.receipt_writer.receipt.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import soo.receipt_writer.receipt.repository.dao.GetMaxSeqDAO;
import soo.receipt_writer.receipt.repository.dao.ReceiptInsertDAO;
import soo.receipt_writer.receipt.repository.dao.ReceiptRemoveDAO;
import soo.receipt_writer.receipt.repository.dao.ReceiptSelectAllDAO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReceiptRepository {

    private final SqlSession session;

    public int insertOne(ReceiptInsertDAO receipt) {
        return session.insert("TB_RECEIPT.insertOne", receipt);
    }

    public long getMaxSeq(GetMaxSeqDAO dao) {
        return session.selectOne("TB_RECEIPT.getMaxSeq", dao);
    }

    public ReceiptInsertDAO selectOne() {
        return session.selectOne("TB_RECEIPT.selectOne");
    }

    public List<ReceiptSelectAllDAO> selectAll(String userId) {
        return session.selectList("TB_RECEIPT.selectAll", userId);
    }

    public int removeReceipt(ReceiptRemoveDAO removeDTO) {
        return session.update("TB_RECEIPT.removeReceipt", removeDTO);
    }

}
