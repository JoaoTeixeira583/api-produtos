package com.example.api_produtos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration 
// marca a classe como um lugar de configuração do Spring
public class SecurityConfig {
    
    @Bean 
    // esse método produz um objeto que deve ficar disponível pra ser injetado em qualquer lugar que precisar
    public PasswordEncoder passwordEncoder(){
        // a implementação concreta que faz a criptografia de verdade
        return new BCryptPasswordEncoder();
    }
}
