package soo.receipt_writer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Receipt {
    private String paymentDate;
    private String paymentAmount;
    private String paymentLocation;
}
