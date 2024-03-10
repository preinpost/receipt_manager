package soo.receipt_writer.receipt.repository.dao;

public record SelectAllByMonthParams(
        String userId,
        String dateStart,
        String dateEnd,
        String sortBy
) {
}
