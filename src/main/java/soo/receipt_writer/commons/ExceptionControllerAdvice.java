package soo.receipt_writer.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import soo.receipt_writer.exceptions.InvalidInputException;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        log.debug("e = {}", e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ErrorResponse handleInvalidInputException(InvalidInputException e) {
        return new ErrorResponse(e.getMessage());
    }
}