package com.guarani.teste_backend.domain.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dataCriacao;

    @Column(nullable = false)
    private Double valorTotal;

    @Column(nullable = false)
    private String status;


    //getters and setters

    public Double getValorTotal(){
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal){
        this.valorTotal = valorTotal;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Date getDataCriacao(){
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao){
        this.dataCriacao = dataCriacao;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

}
