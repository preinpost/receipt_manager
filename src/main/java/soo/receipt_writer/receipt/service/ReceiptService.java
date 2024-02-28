package soo.receipt_writer.receipt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soo.receipt_writer.receipt.repository.Receipt;
import soo.receipt_writer.receipt.repository.ReceiptRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public int addReceipt(Receipt receipt) {
        return receiptRepository.insertOne(receipt);
    }

    public List<Receipt> selectAll() {
        return receiptRepository.selectAll();
    }
}
