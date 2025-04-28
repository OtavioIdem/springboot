package com.guarani.teste_backend.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guarani.teste_backend.domain.dto.PaymentDTO;
import com.guarani.teste_backend.domain.entity.Order;
import com.guarani.teste_backend.domain.entity.Payment;
import com.guarani.teste_backend.domain.repository.OrderRepository;
import com.guarani.teste_backend.domain.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public Payment save(PaymentDTO dto) {
        Order order = orderRepository.findById(dto.orderId())
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Payment payment = Payment.builder()
            .amount(dto.amount())
            .type(dto.type())
            .order(order)
            .build();

        return paymentRepository.save(payment);
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }
}
