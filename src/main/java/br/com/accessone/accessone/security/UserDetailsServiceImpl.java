package br.com.accessone.accessone.security;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import br.com.accessone.accessone.entity.Usuario;
import br.com.accessone.accessone.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl
        implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        String normalizado = email
                .trim()
                .toLowerCase(Locale.ROOT);

        Usuario usuario = usuarioRepository
            .findByEmail(normalizado)
            .orElseThrow(() ->
                new UsernameNotFoundException(
                    "E-mail ou senha inválidos."
                )
            );

        return new UserDetailsImpl(usuario);
    }
}