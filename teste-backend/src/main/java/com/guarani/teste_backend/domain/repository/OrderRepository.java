package com.guarani.teste_backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guarani.teste_backend.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
