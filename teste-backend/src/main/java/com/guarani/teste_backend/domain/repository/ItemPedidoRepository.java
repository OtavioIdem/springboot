package com.guarani.teste_backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guarani.teste_backend.domain.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository <ItemPedido, Long> {
    
}
