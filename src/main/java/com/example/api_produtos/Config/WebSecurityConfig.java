package com.example.api_produtos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class WebSecurityConfig {
    

    @Bean 
    // SecurityFilterChain → é a "cadeia de regras" que toda requisição passa antes de chegar no seu Controller — como um pedágio com várias cabines, cada uma checando uma coisa.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
        // .csrf(csrf -> csrf.disable()) → CSRF é uma proteção pensada pra aplicações web tradicionais (com formulários HTML e sessões). Como sua API é "stateless" (usa JWT, sem sessão), essa proteção específica não se aplica e pode ser desabilitada.
           .csrf(csrf -> csrf.disable())
        //    .authorizeHttpRequests(...) → aqui você define as regras de acesso
           .authorizeHttpRequests(auth -> auth
            // .requestMatchers("/auth/**").permitAll() → "qualquer rota que comece com /auth/ é liberada, sem autenticação" (o ** significa "qualquer coisa depois disso")
               .requestMatchers("/auth/**").permitAll()
            //    .anyRequest().authenticated() → "qualquer outra rota exige autenticação"
               .anyRequest().authenticated()
           );
         
        return http.build();
    }
}
