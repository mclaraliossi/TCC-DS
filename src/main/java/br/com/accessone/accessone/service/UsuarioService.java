package br.com.accessone.accessone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.accessone.accessone.entity.Usuario;
import br.com.accessone.accessone.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario){
        // Criptográfa a senha antes de salvar
        usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
        return usuarioRepository.save(usuario);
      }
    
      public Usuario findById(Integer id){
            return usuarioRepository.findById(id).orElse(null);
        }
    
        public List<Usuario> findAll(){
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
