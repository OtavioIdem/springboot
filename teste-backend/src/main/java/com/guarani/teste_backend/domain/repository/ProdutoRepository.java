package com.guarani.teste_backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guarani.teste_backend.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
    
}
