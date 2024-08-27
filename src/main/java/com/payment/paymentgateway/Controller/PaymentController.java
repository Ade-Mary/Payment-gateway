package com.payment.paymentgateway.Controller;


import com.payment.paymentgateway.dto.PaymentRequestDTO;
import com.payment.paymentgateway.dto.PaymentResponseDTO;
import com.payment.paymentgateway.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initialize")
    public ResponseEntity<PaymentResponseDTO> initializePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO response = paymentService.initializePayment(paymentRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/webhook")
//    public ResponseEntity<String> handlePaystackWebhook(@RequestBody String payload) {
//        try {
//            // Extract event type from the payload
//            String event = extractEventFromPayload(payload);
//            // Handle the webhook event
//            paymentService.handleWebhookEvent(event, payload);
//            return new ResponseEntity<>("Webhook processed successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to process webhook: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    private String extractEventFromPayload(String payload) {
//        // Extract the event type from the payload
//        // Implement the extraction logic
//        return "parsed-event"; // Replace with actual extraction logic
//    }
}