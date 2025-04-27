package com.guarani.teste_backend.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private List<Long> productIds;
    private Double totalAmount;
    private String status;
}
