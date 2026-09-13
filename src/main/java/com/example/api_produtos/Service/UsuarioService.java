package com.example.api_produtos.Service;


import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api_produtos.Entity.Usuario;
import com.example.api_produtos.Repository.UsuarioRepository;

@Service 
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    // Para criar criptografia da senha
    private final PasswordEncoder passwordEncoder;

    // Construtor do usuario 
    public UsuarioService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder){
          this.usuarioRepository = usuarioRepository;
          this.passwordEncoder = passwordEncoder;
    }

    // Metodo para registrar o usuario
    public Usuario registrar(Usuario usuario){
        // Substitui a senha pela versão criptografada
          usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
          return  usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(Usuario usuario){
        // e preciso gaurdar numa variavel no optional pois ele não pode direto num if
        Optional<Usuario> resultado = usuarioRepository.findByNome(usuario.getNome());
        // isPresent ve se o optional esta vazio ou não
        if(resultado.isPresent()){
            // Para ve o usuario direto do banco de dados
             Usuario usuarioEncontrado = resultado.get();

            //  matches() serve para comparação entre dois parametro
             if(passwordEncoder.matches(usuario.getSenha(), usuarioEncontrado.getSenha())){
               return Optional.of(usuarioEncontrado);
             }
        }
        return Optional.empty();
    }


}
