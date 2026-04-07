package com.crud.demo.Controller;

import com.example.prova.ProvaApplication;
import com.example.prova.models.ProdutoModel;
import com.example.prova.services.ProdutoService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtosService;

    //get
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> buscarTodosProdutos() {
        List<ProdutoModel> request = produtosService.buscarTodosProdutos();
        return ResponseEntity.ok().body(request);
    }


    @GetMapping("/{id}")
    public Optional<ProdutoModel> buscarProdutoID(@PathVariable Long id){
        return produtosService.buscarProdutoID(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        produtosService.deletarProduto(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> criar(@RequestBody ProdutoModel produtos) {
        ProdutoModel produto = produtosService.criarProduto(produtos);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping("/{id}")
    public ProdutoModel atualizar(@PathVariable Long id, @RequestBody ProdutoModel produtos) {
        return produtosService.atualizarProduto(produtos, id);
    }

}