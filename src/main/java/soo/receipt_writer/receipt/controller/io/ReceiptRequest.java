package soo.receipt_writer.receipt.controller.io;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ReceiptRequest(
        @NotEmpty
        @Size(min = 8, max = 8)
        String paymentDate,

        @NotEmpty @Digits(integer = 13, fraction = 0) @Min(0)
        String paymentAmount,

        @NotEmpty @Size(max = 20)
        String paymentLocation
) {
}
