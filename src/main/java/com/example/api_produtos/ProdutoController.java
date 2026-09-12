package com.example.api_produtos;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_produtos.exception.ProdutoNaoEncontradoException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController 
// REstController essa classe contém métodos que respondem a requisições HTTP, e o retorno deles deve virar a resposta da requisição"


// @RestController — marca a classe
// @GetMapping — conecta método a URL
// @RequestParam — captura valor depois do ?
// @PathVariable — captura valor dentro do caminho da URL
public class ProdutoController {

    
    // Criar um atributo ProdutoService
    private final ProdutoService produtoService;
  

    // COnstrutor para receber o produtoService
    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
        
    }

    // GetMapping e seria o get do postman
    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
         return produtoService.listarProdutos();
    }

    // Pathvariable pega numeros da url 
    @GetMapping("/produtos/{id}")
    public Produto buscarPorId(@PathVariable Long  id) {
        return produtoService.buscarPorId(id);
    }

    // Metodo Post
    // Para criar um produto em SpringBoot
    @PostMapping("/produtos")
    // @RequestBody Produto produto para colocar no body nome,preco e categoria
    public Produto criarProduto(@RequestBody Produto produto) {
        // Para salvar no banco de dados no produtoService
         return produtoService.salvarProduto(produto);
    }
    
    // Put para atualizar um objeto ja existente
    @PutMapping("/produtos/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produto){
        produto.setId(id);
        return produtoService.salvarProduto(produto);
    }

    // Delete para excluir um no postman
    @DeleteMapping("/produtos/{id}")
    public void  deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id); 
    }

    // Metodo de tratamento especifico no controller no spring boot
     @ExceptionHandler(ProdutoNaoEncontradoException.class)
      public ResponseEntity<String> tratarProdutoNaoEncontrado(ProdutoNaoEncontradoException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
     }
    
    
}
