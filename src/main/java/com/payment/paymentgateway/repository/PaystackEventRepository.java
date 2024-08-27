package com.payment.paymentgateway.repository;


import com.payment.paymentgateway.model.PaystackEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaystackEventRepository extends JpaRepository<PaystackEvent, Long> {
}

