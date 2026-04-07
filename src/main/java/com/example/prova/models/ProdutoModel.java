package com.example.prova.models;


import com.example.prova.Enum.ProdutoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "TBL_PRODUTO")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
    private ProdutoEnum status;

    public ProdutoModel() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }

    public void setPreco(double preco) { this.preco = preco; }

    public ProdutoEnum getStatus() { return status; }

    public void setStatus(ProdutoEnum status) { this.status = status; }
}
