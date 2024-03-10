package soo.receipt_writer.receipt.repository.dao;

public record ReceiptInsertDAO(
        String userId,
        String paymentDate,
        Long seq,
        String paymentAmount,
        String paymentLocation
) {
}
