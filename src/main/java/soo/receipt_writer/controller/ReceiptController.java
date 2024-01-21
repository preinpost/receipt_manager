package soo.receipt_writer.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptController {

    private final SqlSession session;

    public ReceiptController(SqlSession session) {
        this.session = session;
    }

    @GetMapping("/hello")
    public String hello() {
        Object result = session.selectOne("DB_RECEIPT.selectOne");
        System.out.println(result);

        return "Hello, world!";
    }


    @PostMapping("/addReceipt")
    public String addReceipt() {
        Object result = session.insert("DB_RECEIPT.insertOne");
        System.out.println(result);

        return "Hello, world!";
    }


}
