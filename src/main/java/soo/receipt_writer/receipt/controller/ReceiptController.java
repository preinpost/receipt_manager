package soo.receipt_writer.receipt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soo.receipt_writer.commons.SuccessResponse;
import soo.receipt_writer.receipt.repository.Receipt;
import soo.receipt_writer.commons.exceptions.InvalidInputException;
import soo.receipt_writer.receipt.service.ReceiptService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping("/addReceipt")
    public SuccessResponse<Void> addReceipt(@Valid @RequestBody Receipt receipt, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException("입력값을 확인해주세요.");
        }

        receiptService.addReceipt(receipt);

        return SuccessResponse.emptyResponse();
    }

    @GetMapping("/getReceipt")
    public List<Receipt> getAllReceipt() {
        return receiptService.selectAll();
    }
}
