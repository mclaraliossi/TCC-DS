package br.com.accessone.accessone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.accessone.accessone.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    // Verifica se o CNPJ já está cadastrado.
    boolean existsByCnpj(String cnpj);

    // Busca a empresa pelo e-mail do usuário associado.
    // O Spring Data JPA percorre o relacionamento Usuario -> Empresa.
    Optional<Empresa> findByUsuario_Email(String email);
}
