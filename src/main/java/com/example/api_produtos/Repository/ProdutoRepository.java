package com.example.api_produtos.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_produtos.Entity.Produto;

// Uma interface para ter o crud do bd e precisa de parametro o nome da classe com entity e o tipo do id

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    
} 
