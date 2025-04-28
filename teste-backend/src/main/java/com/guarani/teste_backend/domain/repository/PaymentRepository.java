package com.guarani.teste_backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guarani.teste_backend.domain.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> { }
