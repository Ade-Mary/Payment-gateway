package com.payment.paymentgateway.Controller;




import com.payment.paymentgateway.dto.PaystackWebhookDTO;
import com.payment.paymentgateway.service.PaystackWebhookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
public class PaystackWebhookController {

    private final PaystackWebhookService webhookService;

    public PaystackWebhookController(PaystackWebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping("/webhooks")
    public ResponseEntity<String> handlePaystackWebhook(@RequestBody PaystackWebhookDTO webhookDTO,
                                                        @RequestHeader(value = "x-paystack-signature", required = false) String paystackSignature) {
        try {
            // Handle the webhook event
            webhookService.handleWebhook(webhookDTO);
            return new ResponseEntity<>("Webhook processed successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error properly in production
            return new ResponseEntity<>("Failed to process webhook: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
