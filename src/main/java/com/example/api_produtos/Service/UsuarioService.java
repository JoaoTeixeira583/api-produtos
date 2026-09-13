package com.example.api_produtos.Service;

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


}
