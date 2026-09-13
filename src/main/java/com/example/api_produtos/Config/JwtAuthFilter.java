package com.example.api_produtos.Config;

import java.io.IOException;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// OncePerRequestFilte para checar o token antes de chegar no controller
@Component 
public class JwtAuthFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }

    // Para extrair o token e valida e registrar a autenticação do Spring Security
    @Override 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){
           String token = header.substring(7);

           try {
             String nome = jwtService.validarTokenEExtrairNome(token);
            //  SecurityContextHolder que guarda "quem está autenticado nesta requisição". Você precisa colocar essa informação lá dentro, usando um objeto UsernamePasswordAuthenticationToken.
             UsernamePasswordAuthenticationToken authToken =
                   new UsernamePasswordAuthenticationToken(nome, null,Collections.emptyList());
              SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch (Exception e) {
            
           }

        }
        filterChain.doFilter(request, response);   
        
    }
    
}
