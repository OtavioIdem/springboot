package com.guarani.teste_backend.domain.dto;

import java.math.BigDecimal;

public record ProductDTO (
    Long id,
    String name,
    String category,
    BigDecimal price
) {}
