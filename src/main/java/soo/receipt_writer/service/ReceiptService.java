package soo.receipt_writer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import soo.receipt_writer.domain.Receipt;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReceiptService {

    private final SqlSession session;

    public void insertOne(Receipt receipt) {
        int insertCnt = session.insert("DB_RECEIPT.insertOne", receipt);
    }

    public Receipt selectOne() {
        return session.selectOne("DB_RECEIPT.selectOne");
    }

    public List<Receipt> selectAll() {
        return session.selectList("DB_RECEIPT.selectAll");
    }

}
