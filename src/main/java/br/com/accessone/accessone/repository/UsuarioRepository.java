package br.com.accessone.accessone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.accessone.accessone.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Busca um usuário pelo e-mail.
    // Optional representa que o usuário pode não existir.
    Optional<Usuario> findByEmail(String email);

    // Verifica se já existe uma conta com esse e-mail.
    boolean existsByEmail(String email);

}
