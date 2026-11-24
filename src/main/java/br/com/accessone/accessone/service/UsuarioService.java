package br.com.accessone.accessone.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.accessone.accessone.entity.Usuario;
import br.com.accessone.accessone.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) {
        return usuarioRepository
                .findById(id)
                .orElse(null);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorEmail(String email) {

        return usuarioRepository.findByEmail(
            email.trim().toLowerCase(Locale.ROOT)
        );
    }

    public boolean emailExiste(String email) {

        return usuarioRepository.existsByEmail(
            email.trim().toLowerCase(Locale.ROOT)
        );
    }
}