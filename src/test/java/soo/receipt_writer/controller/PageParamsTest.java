package soo.receipt_writer.controller;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PageParamsTest {

    @Test
    public void getNowMonth() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime.getMonthValue());
    }

}