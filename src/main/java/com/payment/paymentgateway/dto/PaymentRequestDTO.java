package com.payment.paymentgateway.dto;
import lombok.Data;
import java.math.BigDecimal;


@Data
public class PaymentRequestDTO {
    private BigDecimal amount;
    private String email;
}

