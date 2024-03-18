package soo.receipt_writer.commons;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
public abstract class CommonResponse<T> implements Serializable {
    private String status;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message = null;
    private T data = null;

    public CommonResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public CommonResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
