package com.payment.paymentgateway.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PaystackWebhookEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String event;
    private String domain;
    private Long amount;
    private String currency;
    private String status;
    private Boolean paid;
    private String requestCode;
    private String description;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;


}

