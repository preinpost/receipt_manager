package soo.receipt_writer.pages;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import soo.receipt_writer.commons.config.LoginUtils;
import soo.receipt_writer.receipt.repository.dao.ReceiptSelectAllDAO;
import soo.receipt_writer.receipt.service.ReceiptService;
import soo.receipt_writer.utils.AmountDisplayUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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

        model.addAttribute("year", pageParams.year());
        model.addAttribute("month", pageParams.month());
        model.addAttribute("receiptList", receiptList);
        model.addAttribute("totalAmount", AmountDisplayUtil.format(totalAmount));
        model.addAttribute("yearList", joinYearFromNowYearSequenceList());

        return "month-list";
    }

    private List<String> joinYearFromNowYearSequenceList() {
        String now = LocalDate.now(ZoneId.of("Asia/Seoul")).toString().replaceAll("-", "").substring(0, 4);
        String joinYear = LoginUtils.loginSession().joinDate().substring(0, 4);

        return IntStream.rangeClosed(Integer.parseInt(joinYear), Integer.parseInt(now))
                .mapToObj(Integer::toString)
                .sorted(Collections.reverseOrder())
                .toList();
    }
}
