package com.guarani.teste_backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.guarani.teste_backend.domain.dto.ProductDTO;
import com.guarani.teste_backend.domain.entity.Product;
import com.guarani.teste_backend.domain.repository.ProductRepository;
import com.guarani.teste_backend.domain.service.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveProductCorrectly() {
        // Arrange
        ProductDTO dto = new ProductDTO(
                null,
                "Produto Teste",
                "Categoria Teste",
                new BigDecimal("99.99")
        );

        Product savedProduct = Product.builder()
                .id(1L)
                .name(dto.name())
                .category(dto.category())
                .price(dto.price())
                .build();

        when(productRepository.save(savedProduct)).thenReturn(savedProduct);

        // Act
        Product result = productService.save(dto);

        // Assert
        assertEquals(dto.name(), result.getName());
        assertEquals(dto.category(), result.getCategory());
        assertEquals(dto.price(), result.getPrice());
    }
}
