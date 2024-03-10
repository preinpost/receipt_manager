package soo.receipt_writer.pages;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import soo.receipt_writer.receipt.repository.dao.ReceiptSelectAllDAO;
import soo.receipt_writer.receipt.service.ReceiptService;
import soo.receipt_writer.utils.AmountDisplayUtil;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PageController {

    private final ReceiptService receiptService;

    @RequestMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("month-list");
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/month-list")
    public String monthList(@Valid PageParams pageParams, BindingResult bindingResult, Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new Exception("TODO");
        }
        log.debug("pageable = {}", pageParams);

        List<ReceiptSelectAllDAO> receiptList = receiptService.selectAll(pageParams);
        Long totalAmount = receiptService.monthTotalAmount(pageParams);

        log.debug("receiptList = {}", receiptList);

        model.addAttribute("year", pageParams.year());
        model.addAttribute("month", pageParams.month());
        model.addAttribute("receiptList", receiptList);
        model.addAttribute("totalAmount", AmountDisplayUtil.format(totalAmount));

        return "month-list";
    }
}
