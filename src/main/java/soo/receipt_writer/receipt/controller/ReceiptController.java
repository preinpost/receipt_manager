package soo.receipt_writer.receipt.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soo.receipt_writer.commons.SuccessResponse;
import soo.receipt_writer.commons.config.LoginUtils;
import soo.receipt_writer.receipt.controller.io.ReceiptRemoveRequest;
import soo.receipt_writer.receipt.controller.io.ReceiptRequest;
import soo.receipt_writer.receipt.repository.Receipt;
import soo.receipt_writer.commons.exceptions.InvalidInputException;
import soo.receipt_writer.receipt.repository.dto.ReceiptRemoveDTO;
import soo.receipt_writer.receipt.repository.dto.ReceiptSelectAllDTO;
import soo.receipt_writer.receipt.service.ReceiptService;
import soo.receipt_writer.users.LoginSession;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping("/addReceipt")
    public SuccessResponse<Void> addReceipt(@Valid @RequestBody ReceiptRequest receipt, BindingResult bindingResult, HttpServletRequest httpRequest) {

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException("입력값을 확인해주세요.");
        }

        LoginSession loginSession = (LoginSession) httpRequest.getSession().getAttribute("loginSession");

        receiptService.addReceipt(Receipt.builder()
                .userId(loginSession.getUserId())
                .receiptYear(receipt.getPaymentDate().substring(0, 4))
                .receiptDate(receipt.getPaymentDate().substring(4, 8))
                .paymentDate(receipt.getPaymentDate())
                .paymentAmount(receipt.getPaymentAmount())
                .paymentLocation(receipt.getPaymentLocation())
                .build()
        );

        return SuccessResponse.emptyResponse();
    }

    @GetMapping("/getReceipt")
    public SuccessResponse<List<ReceiptSelectAllDTO>> getAllReceipt() {
        return new SuccessResponse<>(receiptService.selectAll());
    }

    @PostMapping("/removeReceipt")
    public SuccessResponse<Void> removeReceipt(@Valid @RequestBody ReceiptRemoveRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException("입력값을 확인해주세요.");
        }

        String userId = LoginUtils.loginSession().getUserId();

        receiptService.removeReceipt(new ReceiptRemoveDTO(userId, request.seq(), request.paymentDate()));

        return SuccessResponse.emptyResponse();
    }
}
