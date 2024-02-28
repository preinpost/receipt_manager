package soo.receipt_writer.receipt.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ReceiptRequest {

    @NotEmpty
    @Size(min = 8, max = 8)
    private String paymentDate;

    @NotEmpty @Digits(integer = 13, fraction = 0) @Min(0)
    private String paymentAmount;

    @NotEmpty @Size(max = 20)
    private String paymentLocation;
}
