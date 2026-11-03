
package br.com.accessone.accessone.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.accessone.accessone.entity.Usuario;

// Adapta a entidade Usuario para o formato esperado pelo Spring Security.
public class UserDetailsImpl implements UserDetails {

    private final Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }

    // Retorna a permissão correspondente à role do usuário.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
            new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name())
        );
    }

    // Retorna a senha criptografada que está salva no banco.
    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    // Define o e-mail como identificador de login.
    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    // Indica se a conta não expirou.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indica se a conta não está bloqueada.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indica se as credenciais não expiraram.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indica se a conta está habilitada.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
