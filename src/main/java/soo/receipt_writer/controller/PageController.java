package soo.receipt_writer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import soo.receipt_writer.domain.Receipt;
import soo.receipt_writer.service.ReceiptService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PageController {

    private final ReceiptService receiptService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/month-list")
    public String monthList(@Valid PageParams pageParams, BindingResult bindingResult, Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new Exception("TODO");
        }
        log.debug("pageable = {}", pageParams);

        List<Receipt> receiptList = receiptService.selectAll();

        log.debug("receiptList = {}", receiptList);

        model.addAttribute("receiptList", receiptList);

        return "month-list";
    }
}
