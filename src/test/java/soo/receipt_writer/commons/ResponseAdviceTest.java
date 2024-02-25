package soo.receipt_writer.commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseAdviceTest {


    @Test
    void handleException() {
        RuntimeException exception = new RuntimeException("asd");
        boolean result = RuntimeException.class.isAssignableFrom(exception.getClass());
        System.out.println("result = " + result);


    }

}