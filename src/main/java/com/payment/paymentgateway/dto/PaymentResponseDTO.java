package com.payment.paymentgateway.dto;

public class PaymentResponseDTO {
    private boolean status;
    private String message;
    private PaymentDataDTO data;

    // Getters and Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentDataDTO getData() {
        return data;
    }

    public void setData(PaymentDataDTO data) {
        this.data = data;
    }

    public static class PaymentDataDTO {
        private String authorization_url;
        private String access_code;
        private String reference;

        // Getters and Setters
        public String getAuthorization_url() {
            return authorization_url;
        }

        public void setAuthorization_url(String authorization_url) {
            this.authorization_url = authorization_url;
        }

        public String getAccess_code() {
            return access_code;
        }

        public void setAccess_code(String access_code) {
            this.access_code = access_code;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }
    }
}
