package com.guarani.teste_backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.guarani.teste_backend.domain.dto.PaymentDTO;
import com.guarani.teste_backend.domain.entity.Order;
import com.guarani.teste_backend.domain.entity.Payment;
import com.guarani.teste_backend.domain.enums.PaymentType;
import com.guarani.teste_backend.domain.repository.OrderRepository;
import com.guarani.teste_backend.domain.repository.PaymentRepository;
import com.guarani.teste_backend.domain.service.PaymentService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSavePaymentCorrectly() {
        // Arrange
        Order order = Order.builder()
                .id(1L)
                .build();

        PaymentDTO dto = new PaymentDTO(
                new BigDecimal("150.00"),
                PaymentType.CARTAO_CREDITO,
                1L
        );

        Payment savedPayment = Payment.builder()
                .id(1L)
                .amount(dto.amount())
                .type(dto.type())
                .order(order)
                .build();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(paymentRepository.save(savedPayment)).thenReturn(savedPayment);

        // Act
        Payment result = paymentService.save(dto);

        // Assert
        assertEquals(dto.amount(), result.getAmount());
        assertEquals(dto.type(), result.getType());
        assertEquals(order, result.getOrder());
    }
}
