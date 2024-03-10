package soo.receipt_writer.receipt.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import soo.receipt_writer.receipt.repository.dao.*;

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

    public List<ReceiptSelectAllDAO> selectAllByMonth(SelectAllByMonthParams params) {
        return session.selectList("TB_RECEIPT.selectAllByMonth", params);
    }

    public int removeReceipt(ReceiptRemoveDAO removeDTO) {
        return session.update("TB_RECEIPT.removeReceipt", removeDTO);
    }

    public long monthTotalAmount(ReceiptMonthTotalAmountDAO dao) {
        return session.selectOne("TB_RECEIPT.monthTotalAmount", dao);
    }
}
