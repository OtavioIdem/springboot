package com.guarani.teste_backend.domain.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guarani.teste_backend.domain.entity.Order;
import com.guarani.teste_backend.domain.enums.FormaPagamento;
import com.guarani.teste_backend.domain.enums.OrderStatus;
import com.guarani.teste_backend.domain.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    
     private final OrderService orderService;

    // Criar novo pedido
    @PostMapping
    public Order createOrder(@RequestBody List<Long> productIds) {
        return orderService.createOrder(productIds);
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Listar todos pedidos
    @GetMapping
    public List<Order> listAllOrders() {
        return orderService.listAllOrders();
    }

    // Atualizar status de um pedido
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam("status") OrderStatus status) {
        return orderService.updateOrderStatus(id, status);
    }

    // Atualizar produtos de um pedido
    @PutMapping("/{id}/products")
    public Order updateOrderProducts(@PathVariable Long id, @RequestBody List<Long> productIds) {
        return orderService.updateOrderProducts(id, productIds);
    }

    // Deletar pedido
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/{id}/pagamento")
    public Order adicionarFormaPagamento(
            @PathVariable Long id,
            @RequestParam("formaPagamento") FormaPagamento formaPagamento
    ) {
        return orderService.adicionarFormaPagamento(id, formaPagamento);
    }

    @PutMapping("/{id}/pagamento/confirmar")
    public Order confirmarPagamento(@PathVariable Long id) {
        return orderService.confirmarPagamento(id);
    }



}
