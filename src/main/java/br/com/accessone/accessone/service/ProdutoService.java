package br.com.accessone.accessone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.accessone.accessone.entity.Produto;
import br.com.accessone.accessone.repository.ProdutoRepository;

@Service 
public class ProdutoService {
    
    @Autowired 
    private ProdutoRepository produtoRepository;

     //Método para salvar um cliente
     public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    //Método para listar todos os clientes
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }
    // Método para excluir um cliente por ID
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    // Método para encontrar um cliente por ID
    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }
}
