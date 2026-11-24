package br.com.accessone.accessone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.accessone.accessone.entity.Empresa;

public interface EmpresaRepository
        extends JpaRepository<Empresa, Long> {

    boolean existsByCnpj(String cnpj);

    Optional<Empresa> findByUsuario_Email(String email);

    // Lista empresas com os dados do responsável.
    @EntityGraph(attributePaths = {"usuario"})
    List<Empresa> findAllByOrderByRazaoSocialAsc();
}