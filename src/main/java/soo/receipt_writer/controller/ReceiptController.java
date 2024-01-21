package soo.receipt_writer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soo.receipt_writer.domain.Receipt;
import soo.receipt_writer.service.ReceiptService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping("/addReceipt")
    public void addReceipt(@RequestBody Receipt receipt) {
        receiptService.insertOne(receipt);
    }

    @GetMapping("/getReceipt")
    public List<Receipt> getAllReceipt() {
        return receiptService.selectAll();
    }
}
