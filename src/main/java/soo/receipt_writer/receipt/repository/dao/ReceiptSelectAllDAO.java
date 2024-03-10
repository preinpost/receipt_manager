package soo.receipt_writer.receipt.repository.dao;

import soo.receipt_writer.utils.AmountDisplayUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record ReceiptSelectAllDAO(
        String paymentDate,
        Long seq,
        String paymentAmount,
        String paymentLocation
) {

    public String getPaymentDateWithFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate dateTime = LocalDate.parse(this.paymentDate, DateTimeFormatter.BASIC_ISO_DATE);
        return formatter.format(dateTime);
    }

    public String getPaymentAmountWithComma() {
        return AmountDisplayUtil.format(this.paymentAmount);
    }
}
