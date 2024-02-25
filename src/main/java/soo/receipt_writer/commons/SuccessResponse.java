package soo.receipt_writer.commons;

public class SuccessResponse<T> extends CommonResponse<T> {
    public SuccessResponse(T data) {
        super("success", data);
    }

    static public SuccessResponse<Void> emptyResponse() {
        return new SuccessResponse<>(null);
    }
}