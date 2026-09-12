package com.example.api_produtos;

import java.util.List;


import org.springframework.stereotype.Service;


import com.example.api_produtos.exception.ProdutoNaoEncontradoException;

// Service representa uma classe da lógica da aplicação gerenciada pelo Spring.
@Service 
public class ProdutoService {
    ProdutoRepository produtoRepository;
   
        
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    // Metodo para salvar em spring boot
    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    // Metodo para listarProdutos
    // Usamos o list para uma lista
    public List<Produto> listarProdutos(){
        return  produtoRepository.findAll();
    }

    // Optional no caos de não tiver um id infroamdo
    public Produto buscarPorId(long id){
        return produtoRepository.findById(id)
          .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado com ID:" + id));
    }

//    Metodo para excluir produto Service
    public void deletarProduto(Long id){
         produtoRepository.deleteById(id);
    }

    
}
