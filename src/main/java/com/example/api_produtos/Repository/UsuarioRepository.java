package com.example.api_produtos.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_produtos.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    // Para buscar o usuario pelo nome
    Optional<Usuario> findByNome(String nome);
} 
