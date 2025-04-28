package com.guarani.teste_backend.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guarani.teste_backend.domain.dto.OrderDTO;
import com.guarani.teste_backend.domain.entity.Order;
import com.guarani.teste_backend.domain.entity.Product;
import com.guarani.teste_backend.domain.enums.OrderStatus;
import com.guarani.teste_backend.domain.repository.OrderRepository;
import com.guarani.teste_backend.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order save(OrderDTO dto) {
        List<Product> products = productRepository.findAllById(dto.productIds());

        Order order = Order.builder()
        .id(dto.id())
        .products(products)
        .freightAmount(dto.freightAmount())
        .discountAmount(dto.discountAmount())
        .build();

        order.calculateTotal();
        return orderRepository.save(order);
    }

    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> filterByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> filterByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public List<Order> filterByTotalRange(BigDecimal min, BigDecimal max) {
        return orderRepository.findByTotalBetween(min, max);
    }
}