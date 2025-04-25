package com.guarani.teste_backend.interfaces.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guarani.teste_backend.app.services.PedidoService;
import com.guarani.teste_backend.domain.model.Pedido;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    //Endpoint para um novo pedido
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido();
        pedido.setStatus("Pendente");
        pedido.setDataCriacao(pedidoDTO.getDataCriacao());

        // Convertendo os itens do pedido para o modelo item pedido
        List<ItemPedido> itensPedido = pedidoDTO.getItensPedido();

        // Criando o pedido
        Pedido pedidoCriado = pedidoService.criarPedido(pedido, itensPedido);

        return ResponseEntity.ok(pedidoCriado);
    }
}
