package com.guarani.teste_backend.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.guarani.teste_backend.domain.enums.OrderStatus;

public record OrderDTO(
    Long id,
    LocalDateTime createdAt,
    OrderStatus status,
    BigDecimal total,
    BigDecimal freightAmount,
    BigDecimal discountAmount,
    List<Long> productIds
) { }
