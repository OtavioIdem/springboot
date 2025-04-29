package com.guarani.teste_backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.guarani.teste_backend.domain.dto.OrderDTO;
import com.guarani.teste_backend.domain.entity.Order;
import com.guarani.teste_backend.domain.entity.Product;
import com.guarani.teste_backend.domain.repository.OrderRepository;
import com.guarani.teste_backend.domain.repository.ProductRepository;
import com.guarani.teste_backend.domain.service.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCalculateTotalWithFreightAndDiscountCorrectly() {

        Product product1 = Product.builder()
                .id(1L)
                .name("Produto A")
                .price(new BigDecimal("100"))
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .name("Produto B")
                .price(new BigDecimal("200"))
                .build();

        List<Product> products = List.of(product1, product2);

        OrderDTO dto = new OrderDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(1L, 2L)
        );

        when(productRepository.findAllById(List.of(1L, 2L))).thenReturn(products);

        Order savedOrder = orderService.save(dto);

        BigDecimal expectedProductsTotal = new BigDecimal("300"); // 100 + 200
        BigDecimal expectedFreight = new BigDecimal("15.00");
        BigDecimal expectedDiscount = expectedProductsTotal.multiply(new BigDecimal("0.10")); // 10% de desconto

        BigDecimal expectedTotal = expectedProductsTotal.add(expectedFreight).subtract(expectedDiscount);

        assertEquals(expectedFreight, savedOrder.getFreightAmount());
        assertEquals(expectedDiscount, savedOrder.getDiscountAmount());
        assertEquals(expectedTotal, savedOrder.getTotal());
    }
}
