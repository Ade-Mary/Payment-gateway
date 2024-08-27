package com.payment.paymentgateway.repository;
import com.payment.paymentgateway.model.PaystackWebhookEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaystackWebhookRepository extends JpaRepository<PaystackWebhookEvent, Long> {
}

