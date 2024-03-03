package soo.receipt_writer.receipt.repository;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter @Setter @Builder
@AllArgsConstructor
public class Receipt {

    private String userId;

    private String receiptYear;

    private String receiptDate;

    private Long seq;

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
