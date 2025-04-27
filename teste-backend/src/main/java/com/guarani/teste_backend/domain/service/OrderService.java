package com.guarani.teste_backend.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guarani.teste_backend.domain.entity.Order;
import com.guarani.teste_backend.domain.entity.Pagamento;
import com.guarani.teste_backend.domain.entity.Product;
import com.guarani.teste_backend.domain.enums.FormaPagamento;
import com.guarani.teste_backend.domain.enums.OrderStatus;
import com.guarani.teste_backend.domain.repository.OrderRepository;
import com.guarani.teste_backend.domain.repository.ProductRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // Criar um novo pedido
    @Transactional
    public Order createOrder(List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new RuntimeException("Produtos não encontrados");
        }

        Order order = new Order();
        order.setProducts(products);
        order.setStatus(OrderStatus.AGUARDANDO_PAGAMENTO);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order.calcularValorTotal();

        return orderRepository.save(order);
    }

    // Buscar pedido por ID
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
    }

    // Listar todos os pedidos
    @Transactional(readOnly = true)
    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }

    // Atualizar um pedido (por exemplo, o status)
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        order.setStatus(newStatus);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // Atualizar produtos de um pedido (e recalcular total)
    @Transactional
    public Order updateOrderProducts(Long orderId, List<Long> productIds) {
        Order order = getOrderById(orderId);
        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado");
        }
        order.setProducts(products);
        order.calcularValorTotal();
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // Deletar um pedido
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado para exclusão");
        }
        orderRepository.deleteById(id);
    }

    public Order adicionarFormaPagamento(Long idPedido, FormaPagamento formaPagamento) {
        Order pedido =getOrderById(idPedido);

        Pagamento pagamento = Pagamento.builder()
                .formaPagamento(formaPagamento)
                .confirmado(false) // pagamento começa como não confirmado
                .build();

        pedido.setPagamento(pagamento);

        return orderRepository.save(pedido);
    }

    @Transactional
    public Order confirmarPagamento(Long idPedido) {
        Order pedido = getOrderById(idPedido);

        if (pedido.getPagamento() == null) {
            throw new RuntimeException("Pedido ainda não possui forma de pagamento definida");
        }

        pedido.getPagamento().setConfirmado(true);

        return orderRepository.save(pedido);
    }



}
