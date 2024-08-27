package com.payment.paymentgateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.paymentgateway.dto.PaystackWebhookDTO;
import com.payment.paymentgateway.model.PaystackWebhookEvent;
import com.payment.paymentgateway.repository.PaystackWebhookRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PaystackWebhookService {

    private final PaystackWebhookRepository webhookRepository;

    public PaystackWebhookService(PaystackWebhookRepository webhookRepository) {
        this.webhookRepository = webhookRepository;
    }

    public void handleWebhook(PaystackWebhookDTO webhookDTO) {
        // Process the event data
        processEvent(webhookDTO);
    }

    private void processEvent(PaystackWebhookDTO webhookDTO) {
        PaystackWebhookEvent event = new PaystackWebhookEvent();

        event.setId(webhookDTO.getData().getId());
        event.setEvent(webhookDTO.getEvent());
        event.setDomain(webhookDTO.getData().getDomain());
        event.setAmount(webhookDTO.getData().getAmount());
        event.setCurrency(webhookDTO.getData().getCurrency());
        event.setStatus(webhookDTO.getData().getStatus());
        event.setPaid(webhookDTO.getData().getPaid());
        event.setRequestCode(webhookDTO.getData().getRequest_code());
        event.setDescription(webhookDTO.getData().getDescription());

        // Parse date-time strings with timezone offset
        event.setPaidAt(parseDateTime(webhookDTO.getData().getPaid_at()).toLocalDateTime());
        event.setCreatedAt(parseDateTime(webhookDTO.getData().getCreated_at()).toLocalDateTime());

        webhookRepository.save(event);
    }

    private OffsetDateTime parseDateTime(String dateTime) {
        return OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    }
}
