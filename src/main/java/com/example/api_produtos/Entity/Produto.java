package com.example.api_produtos.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Entity transforma essa classe uma tabela no banco de dados
@Entity
public class Produto {

    @Id 
    // Esse campo e para mostrar a chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Esse codigo forma o ida automaticamnete
    private Long id;
    // Long consegue uma quantidade muito maior de identificadores 

    private String nome;
    private double preco;
    private String categoria;

    // COnstrutor vazio permite Hibernate crie a entidade
    public Produto(){

    }

    public Produto(String nome, double preco, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
