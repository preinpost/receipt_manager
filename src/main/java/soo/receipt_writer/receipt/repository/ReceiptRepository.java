package soo.receipt_writer.receipt.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import soo.receipt_writer.receipt.repository.dto.ReceiptRemoveDTO;
import soo.receipt_writer.receipt.repository.dto.ReceiptSelectAllDTO;

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

    public List<ReceiptSelectAllDTO> selectAll(String userId) {
        return session.selectList("TB_RECEIPT.selectAll", userId);
    }

    public int removeReceipt(ReceiptRemoveDTO removeDTO) {
        return session.update("TB_RECEIPT.removeReceipt", removeDTO);
    }
}
