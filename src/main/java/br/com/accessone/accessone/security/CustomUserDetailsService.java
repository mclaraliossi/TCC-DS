package br.com.accessone.accessone.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.accessone.accessone.entity.Usuario;
import br.com.accessone.accessone.service.UsuarioService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public CustomUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Método chamado pelo Spring Security durante a autenticação.
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // Procura a conta pelo e-mail informado no login.
        Usuario usuario = usuarioService.buscarPorEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(
                "E-mail ou senha inválidos."
            ));

        // Entrega ao Spring Security o e-mail, a senha codificada
        // e as permissões associadas à conta.
        return User.withUsername(usuario.getEmail())
            .password(usuario.getSenha())
            .roles(usuario.getRole().name())
            .build();
    }
}