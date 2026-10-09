package br.com.accessone.accessone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.accessone.accessone.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Integer, Produto> {
    
}
