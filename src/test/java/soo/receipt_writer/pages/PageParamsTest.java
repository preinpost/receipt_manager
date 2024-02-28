package soo.receipt_writer.pages;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PageParamsTest {

    @Test
    public void getNowMonth() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime.getMonthValue());
    }

}