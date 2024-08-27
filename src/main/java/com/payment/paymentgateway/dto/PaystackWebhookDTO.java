package com.payment.paymentgateway.dto;

import lombok.Data;

@Data
public class PaystackWebhookDTO {
    private String event;
    private Data data;


@lombok.Data
    public static class Data {
        private Long id;
        private String domain;
        private Long amount;
        private String currency;
        private String status;
        private Boolean paid;
        private String request_code;
        private String description;
        private String paid_at;
        private String created_at;

    }
}

