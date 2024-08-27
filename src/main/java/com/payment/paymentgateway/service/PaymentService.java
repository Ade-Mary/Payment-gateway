package com.payment.paymentgateway.service;
import com.payment.paymentgateway.dto.PaymentRequestDTO;
import com.payment.paymentgateway.dto.PaymentResponseDTO;
import com.payment.paymentgateway.model.Customer;
import com.payment.paymentgateway.model.Payment;
import com.payment.paymentgateway.repository.CustomerRepository;
import com.payment.paymentgateway.repository.PaymentRepository;
import com.payment.paymentgateway.repository.PaystackWebhookRepository;
import com.payment.paymentgateway.util.PaystackUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;


@Service
public class PaymentService {

    private final PaystackUtil paystackUtil;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final PaystackWebhookRepository webhookEventRepository;

    @Autowired
    public PaymentService(PaystackUtil paystackUtil, PaymentRepository paymentRepository,
                          CustomerRepository customerRepository, PaystackWebhookRepository webhookEventRepository) {
        this.paystackUtil = paystackUtil;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
        this.webhookEventRepository = webhookEventRepository;
    }

    @Transactional
    public PaymentResponseDTO initializePayment(PaymentRequestDTO paymentRequestDTO) {
        // Create or get existing customer
        Customer customer = customerRepository.findByEmail(paymentRequestDTO.getEmail());
        if (customer == null) {
            customer = new Customer();
            customer.setEmail(paymentRequestDTO.getEmail());
            customerRepository.save(customer);
        }

        // Initialize payment via Paystack
        PaymentResponseDTO paymentResponseDTO = paystackUtil.initializePayment(paymentRequestDTO);

        // Create payment record
        Payment payment = new Payment();
        payment.setTransactionStatus("pending");
        payment.setCreatedAt(LocalDateTime.now());
        payment.setCustomer(customer);
        paymentRepository.save(payment);

        return paymentResponseDTO;
    }

}