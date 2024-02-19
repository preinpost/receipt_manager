package soo.receipt_writer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@Data
@AllArgsConstructor
public class Receipt {
    private String paymentDate;
    private String paymentAmount;
    private String paymentLocation;

    public String getPaymentDateWithFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate dateTime = LocalDate.parse(this.paymentDate, DateTimeFormatter.BASIC_ISO_DATE);
        return formatter.format(dateTime);
    }

    public String getPaymentAmountWithComma() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(Long.parseLong(this.paymentAmount));
    }
}
