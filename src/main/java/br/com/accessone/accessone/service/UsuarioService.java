
package br.com.accessone.accessone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.accessone.accessone.entity.Usuario;
import br.com.accessone.accessone.repository.UsuarioRepository;

// Indica ao Spring que esta classe contém lógica de serviço.
@Service
public class UsuarioService {

    // Dependência responsável por consultar e salvar os usuários.
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Responsável por criptografar as senhas.
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Salva o usuário após criptografar sua senha.
    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    // Busca um usuário pelo seu ID.
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Retorna todos os usuários cadastrados.
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Busca uma conta pelo e-mail normalizado.
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(
            email.trim().toLowerCase()
        );
    }

    // Verifica se o e-mail já está cadastrado.
    public boolean emailExiste(String email) {
        return usuarioRepository.existsByEmail(
            email.trim().toLowerCase()
        );
    }

}
