package soo.receipt_writer.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import soo.receipt_writer.commons.exceptions.InvalidInputException;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("관리자에게 문의해주세요."), HttpStatus.BAD_REQUEST);
    }
}