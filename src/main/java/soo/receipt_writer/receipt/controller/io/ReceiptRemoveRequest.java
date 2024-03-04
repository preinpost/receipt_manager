package soo.receipt_writer.receipt.controller.io;

import jakarta.validation.constraints.NotNull;

public record ReceiptRemoveRequest(
        @NotNull long seq,
        @NotNull String paymentDate
) {
}
