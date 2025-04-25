package com.guarani.teste_backend.app.services;

import org.springframework.stereotype.Service;

import com.guarani.teste_backend.domain.model.Produto;
import com.guarani.teste_backend.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    //Metodo para criar um novo produto
    public Produto criarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    //Metodo para atualizar o produto existente
    public Produto atualizarProduto(Long produtoId, Produto produtoAtualizado){
        Produto produtoExistente = produtoRepository.findById(produtoId)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());

        return produtoRepository.save(produtoExistente);
    }

    //Metodo para excluir um produto
    public void excluirProduto(Long produtoId){
        // Por boas praticas nao seria ideal deletar o produto e sim alterar uma flag de status de ativo para inativo
        produtoRepository.deleteById(produtoId);
    }
}
