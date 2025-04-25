package com.guarani.teste_backend.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guarani.teste_backend.domain.model.ItemPedido;
import com.guarani.teste_backend.domain.model.Pedido;
import com.guarani.teste_backend.domain.repository.ItemPedidoRepository;
import com.guarani.teste_backend.domain.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository){
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }


    //Método criado para criar um pedido
    public Pedido criarPedido(Pedido pedido, List<ItemPedido> itensPedido){
        double valorTotal = calcularValorTotal(itensPedido);

        pedido.setValorTotal(valorTotal);
        pedido.setStatus("Pendente");  //O status do pedido pode ser algo como Pendente, Em Processamento, etc

        //Salvar o pedido
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for(ItemPedido item : itensPedido){
            item.setPedido(pedidoSalvo);        //Associa o pedido ao item
            itemPedidoRepository.save(item);     //Salva os itens no banco de dados
        }

        return pedidoSalvo;
    }


    private double calcularValorTotal(List<ItemPedido> itensPedido){
        return itensPedido.stream()
                .mapToDouble(item -> item.getValor())
                .sum();
    }
}
