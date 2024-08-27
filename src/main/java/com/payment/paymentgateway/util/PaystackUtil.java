package com.payment.paymentgateway.util;



import com.payment.paymentgateway.dto.PaymentRequestDTO;
import com.payment.paymentgateway.dto.PaymentResponseDTO;
import com.payment.paymentgateway.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PaystackUtil {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${paystack.secret.key}")
    private String paystackSecretKey;

    private static final String PAYSTACK_INITIALIZE_URL = "https://api.paystack.co/transaction/initialize";
    private static final String PAYSTACK_VERIFY_URL = "https://api.paystack.co/transaction/verify/";

    public String generatePaymentReference() {
        return UUID.randomUUID().toString();
    }

    public PaymentResponseDTO initializePayment(PaymentRequestDTO paymentRequestDTO) {
        // Convert amount to kobo using BigDecimal for precision
        BigDecimal amountInKobo = paymentRequestDTO.getAmount().multiply(BigDecimal.valueOf(100));
        paymentRequestDTO.setAmount(amountInKobo);

        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(paystackSecretKey);

        // Create the request entity
        HttpEntity<PaymentRequestDTO> requestEntity = new HttpEntity<>(paymentRequestDTO, headers);

        // Make the POST request to initialize the payment
        ResponseEntity<PaymentResponseDTO> response = restTemplate.exchange(
                PAYSTACK_INITIALIZE_URL,
                HttpMethod.POST,
                requestEntity,
                PaymentResponseDTO.class
        );

        // Handle response status and return response body as PaymentResponseDTO
        if (response.getStatusCode() == HttpStatus.OK) {
            PaymentResponseDTO responseDTO = response.getBody();
            if (responseDTO != null && responseDTO.getData() != null) {
                System.out.println("Authorization URL: " + responseDTO.getData().getAuthorization_url());
                System.out.println("Access Code: " + responseDTO.getData().getAccess_code());
                System.out.println("Payment Reference: " + responseDTO.getData().getReference());
                return responseDTO;
            } else {
                throw new RuntimeException("Failed to initialize payment. Data is null.");
            }
        } else {
            throw new RuntimeException("Failed to initialize payment: " + response.getStatusCode());
        }
    }


    public String verifyPayment(String paymentReference) {
        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(paystackSecretKey);

        // Create the request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Make the GET request to verify the payment
        ResponseEntity<String> response = restTemplate.exchange(
                PAYSTACK_VERIFY_URL + paymentReference,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        // Return the response body
        return response.getBody();
    }
}
