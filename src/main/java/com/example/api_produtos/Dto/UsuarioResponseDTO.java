package com.example.api_produtos.Dto;

// esse metodo e para mostrar o resultaod sem ter a senha 
// representa oq o clinete vai ver
// DTO existe pra separar "o que está salvo no banco" de "o que a API expõe"
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    
    public UsuarioResponseDTO(Long id,String nome){
        this.nome = nome;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
