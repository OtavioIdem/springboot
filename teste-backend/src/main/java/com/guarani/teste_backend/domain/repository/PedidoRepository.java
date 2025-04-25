package com.guarani.teste_backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guarani.teste_backend.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido, Long> {
    
}
