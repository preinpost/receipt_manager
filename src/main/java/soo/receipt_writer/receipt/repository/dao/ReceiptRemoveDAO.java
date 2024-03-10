package soo.receipt_writer.receipt.repository.dao;

public record ReceiptRemoveDAO(
        String userId,
        long seq,
        String paymentDate
) {
}