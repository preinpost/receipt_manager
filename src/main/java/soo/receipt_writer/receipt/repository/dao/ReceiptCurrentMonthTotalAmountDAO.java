package soo.receipt_writer.receipt.repository.dao;

public record ReceiptCurrentMonthTotalAmountDAO(
        String userId,
        String receiptYear,
        String receiptMonth
) {
}
