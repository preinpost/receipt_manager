package soo.receipt_writer.commons;

public class ErrorResponse extends CommonResponse {
    public ErrorResponse(String message) {
        super("fail", message);
    }
}
