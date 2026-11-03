
package br.com.accessone.accessone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.accessone.accessone.entity.Usuario;
import br.com.accessone.accessone.repository.UsuarioRepository;

// Busca no banco o usuário que está tentando fazer login.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Procura o usuário pelo e-mail informado na tela de login.
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(
                "Usuário não encontrado: " + email
            ));

        return new UserDetailsImpl(usuario);
    }
}
