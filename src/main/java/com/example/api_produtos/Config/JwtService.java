package com.example.api_produtos.Config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
// Token  e para mostrar que vc é sem gastar memoria da sua api

// Service "essa classe é um componente gerenciado pelo Spring".
@Service 
public class JwtService {

    // getBytes transforma esse texto numa sequencia em bytes
    // Keys.hmacShaKeyFor(bytes) pega esses bytes e monta um objeto SecretKey

    private final SecretKey key = Keys.hmacShaKeyFor("uma-chave-de-pelo-menos-32-caracteres-aqui".getBytes());

    // Esse metodo e para gerar token
    // Ele roda um vez depois do momento do login
    public String gerarToken(String nome){
        // Jwts.builder() começa a "montagem" de um token novo. É o padrão builder: você vai empilhando configurações, uma chamada de método por vez, até fechar no final.
        return Jwts.builder()
            .subject(nome)              // o "dono" do token
            .issuedAt(new Date())       // quando foi criado
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // quando expira
            .signWith(key)              // assina com a chave secreta
            .compact();                 // gera a string final
    }

    // Metodo para validarToken
    public String validarTokenEExtrairNome(String token) {
    return Jwts.parser() // para ler o token
        .verifyWith(key) // pra verificar a assinatura, use esta chave secreta" — a mesma key usada lá no gerarToken. Assinar e verificar usam a mesma chave (é o mesmo esquema simétrico).
        .build() // Fecha a configuração e entrega um objeto JwtParser pronto pra uso
        .parseSignedClaims(token) // pega a key e compara a que veio no token se for passa se não lança exceção
        .getPayload()
        .getSubject();
    }
}
