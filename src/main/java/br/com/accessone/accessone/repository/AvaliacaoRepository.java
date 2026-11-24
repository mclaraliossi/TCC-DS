package br.com.accessone.accessone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.accessone.accessone.entity.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByEmpresa_IdOrderByDataDesc(
        Long empresaId
    );
}