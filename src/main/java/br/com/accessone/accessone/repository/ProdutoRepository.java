package br.com.accessone.accessone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.accessone.accessone.entity.Produto;

public interface ProdutoRepository
        extends JpaRepository<Produto, Long> {

    // Lista produtos de uma empresa.
    List<Produto> findByEmpresa_IdOrderByIdProdutoDesc(
        Long empresaId
    );

    // Busca o produto somente se pertencer à empresa.
    Optional<Produto> findByIdProdutoAndEmpresa_Id(
        Long idProduto,
        Long empresaId
    );

    // Conta os produtos pertencentes à empresa.
    long countByEmpresa_Id(Long empresaId);
}