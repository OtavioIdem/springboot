package com.guarani.teste_backend.interfaces.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guarani.teste_backend.app.services.ProdutoService;
import com.guarani.teste_backend.domain.model.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    //Endpoint para novo produto
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        Produto produtoCriado = produtoService.criarProduto(produto);

        return ResponseEntity.ok(produtoCriado);
    }

    //Endpoint para atualizar o produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto){
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);

        return ResponseEntity.ok(produtoAtualizado);
    }

    //Endpoint para deletar o produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id){
        produtoService.excluirProduto(id);

        return ResponseEntity.noContent().build();
    }

}
