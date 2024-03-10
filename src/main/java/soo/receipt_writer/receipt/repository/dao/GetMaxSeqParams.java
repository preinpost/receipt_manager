package soo.receipt_writer.receipt.repository.dao;

public record GetMaxSeqParams(
        String userId,
        String paymentDate
) {
}
