package com.guarani.teste_backend.domain.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.order-created.queue}")
    private String orderCreatedQueue;

    public void sendOrderCreatedMessage(String message) {
        rabbitTemplate.convertAndSend(orderCreatedQueue, message);
    }
}
