package soo.receipt_writer.commons;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SuccessResponse {

    private final String status = "success";
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final Object data;

    public SuccessResponse(Object data) {
        this.data = data;
    }

    public SuccessResponse() {
        this.data = "{}";
    }
}