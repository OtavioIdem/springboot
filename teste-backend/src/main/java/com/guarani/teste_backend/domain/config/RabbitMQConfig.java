package com.guarani.teste_backend.domain.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.order-created.queue}")
    private String orderCreatedQueue;

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(orderCreatedQueue, true); // true = durable
    }
}
