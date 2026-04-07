package com.example.prova.controller;


import com.example.prova.models.ProdutoModel;
import com.example.prova.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoModel>> buscarProdutoID(@PathVariable Long id) {
        Optional<ProdutoModel> requisicao = produtoService.buscarProdutoID(id);
        if (requisicao.isPresent()) {
            return ResponseEntity.ok(requisicao);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<List<ProdutoModel>> buscarTodosProdutos() {
        List<ProdutoModel> produtos = produtoService.buscarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> atualizarProduto(@RequestBody ProdutoModel produtoModel,
                                                         @PathVariable Long id) {
        ProdutoModel produto = produtoService.atualizarProduto(produtoModel, id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> criarProduto(@RequestBody ProdutoModel produtoModel) {
        ProdutoModel produto = produtoService.criarProduto(produtoModel);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(produtoModel.getId())
                .toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @DeleteMapping
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        if(produtoService.buscarProdutoID(id).isPresent()) {
            produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}