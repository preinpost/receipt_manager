package soo.receipt_writer.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class PageController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/month-list")
    public String monthList(@Valid PageParams pageParams, BindingResult bindingResult) {
        log.debug("bindingResult = {}", bindingResult.getErrorCount());

        log.debug("pageable = {}", pageParams);


        return "month-list";
    }


}
