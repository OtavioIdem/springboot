package com.guarani.teste_backend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.guarani.teste_backend.domain.entity.Order;
import com.guarani.teste_backend.domain.enums.OrderStatus;
import com.guarani.teste_backend.domain.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public Map<String, Object> reportByDateRange(LocalDateTime start, LocalDateTime end) {
        List<Order> orders = orderRepository.findByCreatedAtBetween(start, end);

        BigDecimal totalSales = orders.stream()
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Map.of(
                "totalOrders", orders.size(),
                "totalSales", totalSales
        );
    }

    public Map<String, Object> reportByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.findByStatus(status);

        BigDecimal totalSales = orders.stream()
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Map.of(
                "totalOrders", orders.size(),
                "totalSales", totalSales
        );
    }
}
