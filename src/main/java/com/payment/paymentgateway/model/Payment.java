package com.payment.paymentgateway.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String transactionId;

    @Column(unique = true)
    private String reference;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



    private BigDecimal amountPaid;


    private String currency;


    private String transactionStatus;


    private String paymentMethod;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paymentGatewayResponse;


    private String verificationStatus = "unverified";

    private String refundStatus;

    private BigDecimal refundAmount;

    @Lob
    private String refundReason;


    private LocalDateTime paymentInitiationTime;

    private LocalDateTime paymentCompletionTime;

    private LocalDateTime verificationTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


}