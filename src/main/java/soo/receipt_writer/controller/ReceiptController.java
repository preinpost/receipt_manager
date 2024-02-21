package soo.receipt_writer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soo.receipt_writer.commons.SuccessResponse;
import soo.receipt_writer.domain.Receipt;
import soo.receipt_writer.exceptions.InvalidInputException;
import soo.receipt_writer.service.ReceiptService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping("/addReceipt")
    public ResponseEntity<SuccessResponse> addReceipt(@Valid @RequestBody Receipt receipt, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException("입력값을 확인해주세요.");
        }

        receiptService.addReceipt(receipt);

        return ResponseEntity.ok(new SuccessResponse());
    }

    @GetMapping("/getReceipt")
    public List<Receipt> getAllReceipt() {
        return receiptService.selectAll();
    }
}
