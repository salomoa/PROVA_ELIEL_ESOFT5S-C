package com.example.prova.services;


import com.example.prova.models.ProdutoModel;
import com.example.prova.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Optional<ProdutoModel> buscarProdutoID(Long id) { return produtoRepository.findById(id); }

    public List<ProdutoModel> buscarTodosProdutos() { return produtoRepository.findAll(); }

    public void deletarProduto(Long id) { produtoRepository.deleteById(id); }

    public ProdutoModel criarProduto(ProdutoModel produtoModel) { return produtoRepository.save(produtoModel); }

    public ProdutoModel atualizarProduto(ProdutoModel produtoModel, Long id) {
        ProdutoModel model = produtoRepository.findById(id).get();
        model.setNome(produtoModel.getNome());
        model.setPreco(produtoModel.getPreco());
        model.setDescricao(produtoModel.getDescricao());
        model.setStatus(produtoModel.getStatus());
        return produtoRepository.save(model);
    }

}
