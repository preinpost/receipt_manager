package soo.receipt_writer.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Data
@AllArgsConstructor
public class Receipt {

    @NotEmpty @Size(min = 8, max = 8)
    private String paymentDate;

    @NotEmpty @Digits(integer = 13, fraction = 0) @Min(0)
    private String paymentAmount;

    @NotEmpty @Size(max = 20)
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
