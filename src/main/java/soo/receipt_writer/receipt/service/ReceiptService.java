package soo.receipt_writer.receipt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soo.receipt_writer.commons.config.LoginUtils;
import soo.receipt_writer.commons.exceptions.InvalidInputException;
import soo.receipt_writer.receipt.controller.io.ReceiptRemoveRequest;
import soo.receipt_writer.receipt.controller.io.ReceiptRequest;
import soo.receipt_writer.receipt.repository.dao.GetMaxSeqDAO;
import soo.receipt_writer.receipt.repository.dao.ReceiptRemoveDAO;
import soo.receipt_writer.receipt.repository.dao.ReceiptInsertDAO;
import soo.receipt_writer.receipt.repository.ReceiptRepository;
import soo.receipt_writer.receipt.repository.dao.ReceiptSelectAllDAO;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public int addReceipt(ReceiptRequest receipt) {

        long maxSeq = receiptRepository.getMaxSeq(
                new GetMaxSeqDAO(LoginUtils.loginSession().getUserId(), receipt.paymentDate())
        );

        int result = receiptRepository.insertOne(
                new ReceiptInsertDAO(
                        LoginUtils.loginSession().getUserId(),
                        receipt.paymentDate(),
                        maxSeq,
                        receipt.paymentAmount(),
                        receipt.paymentLocation()
                )
        );

        if (result == 0) {
            throw new InvalidInputException("영수증 추가에 실패했습니다.");
        }

        return result;
    }

    public List<ReceiptSelectAllDAO> selectAll() {
        return receiptRepository.selectAll(LoginUtils.loginSession().getUserId());
    }

    public int removeReceipt(ReceiptRemoveRequest request) {

        String userId = LoginUtils.loginSession().getUserId();

        ReceiptRemoveDAO removeDTO = new ReceiptRemoveDAO(userId, request.seq(), request.paymentDate());
        return receiptRepository.removeReceipt(removeDTO);
    }
}
