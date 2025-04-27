package com.guarani.teste_backend.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
    private Long produtoId;
    private Integer quantidade;
}
