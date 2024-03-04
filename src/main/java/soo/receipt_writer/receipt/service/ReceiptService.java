package soo.receipt_writer.receipt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soo.receipt_writer.commons.config.LoginUtils;
import soo.receipt_writer.commons.exceptions.InvalidInputException;
import soo.receipt_writer.receipt.repository.dto.ReceiptRemoveDTO;
import soo.receipt_writer.receipt.repository.Receipt;
import soo.receipt_writer.receipt.repository.ReceiptRepository;
import soo.receipt_writer.receipt.repository.dto.ReceiptSelectAllDTO;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public int addReceipt(Receipt receipt) {

        long maxSeq = receiptRepository.getMaxSeq(receipt);
        receipt.setSeq(maxSeq);

        int result = receiptRepository.insertOne(receipt);

        if (result == 0) {
            throw new InvalidInputException("영수증 추가에 실패했습니다.");
        }

        return result;
    }

    public List<ReceiptSelectAllDTO> selectAll() {
        return receiptRepository.selectAll(LoginUtils.loginSession().getUserId());
    }

    public int removeReceipt(ReceiptRemoveDTO removeDTO) {
        return receiptRepository.removeReceipt(removeDTO);
    }
}
