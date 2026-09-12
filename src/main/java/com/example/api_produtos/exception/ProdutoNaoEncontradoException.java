package com.example.api_produtos.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{
     public ProdutoNaoEncontradoException(String mensagem){
        super(mensagem);
     }
}
