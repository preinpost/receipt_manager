package soo.receipt_writer.receipt.repository.dao;

public record ReceiptMonthTotalAmountDAO(
        String userId,
        String dateStart,
        String dateEnd
) {
}
