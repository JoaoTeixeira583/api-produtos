package com.example.api_produtos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Diz que essa classe define a configurações de sua aplicação
@Configuration 
// E o lugar onde define a regras de segurança da sua api
public class WebSecurityConfig {
    

   // Esse método monta a cadeia de segurança — a sequência de checagens que toda requisição passa antes de chegar no seu Controller.
    @Bean 
    // SecurityFilterChain → é a "cadeia de regras" que toda requisição passa antes de chegar no seu Controller — como um pedágio com várias cabines, cada uma checando uma coisa.
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthFilter jwtAuthFilter) throws Exception{
        http
        // .csrf(csrf -> cs   rf.disable()) → CSRF é uma proteção pensada pra aplicações web tradicionais (com formulários HTML e sessões). Como sua API é "stateless" (usa JWT, sem sessão), essa proteção específica não se aplica e pode ser desabilitada.
           .csrf(csrf -> csrf.disable())

           .addFilterBefore(jwtAuthFilter, 
            UsernamePasswordAuthenticationFilter.class)

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
