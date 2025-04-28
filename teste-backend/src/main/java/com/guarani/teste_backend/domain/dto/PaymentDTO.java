package com.guarani.teste_backend.domain.dto;

import java.math.BigDecimal;
import com.guarani.teste_backend.domain.enums.PaymentType;

public record PaymentDTO(
    BigDecimal amount,
    PaymentType type,
    Long orderId
) {
}
