package br.com.accessone.accessone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Disponibiliza o BCrypt para codificar e verificar senhas.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define as regras de autenticação e autorização HTTP.
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                // Páginas que podem ser acessadas sem login.
                .requestMatchers(
                    "/login",
                    "/cadastro/empresa",
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()

                // Somente administradores acessam estas rotas.
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // Somente empresas autenticadas acessam estas rotas.
                .requestMatchers("/empresa/**").hasRole("EMPRESA")

                // Todas as outras rotas exigem autenticação.
                .anyRequest().authenticated()
            )

            // Configura a página de login personalizada.
            .formLogin(form -> form
                .loginPage("/accessone/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/redirecionar", true)
                .failureUrl("/accessone/login?error")
                .permitAll()
            )

            // Configura o destino após sair da conta.
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        // Mantém a proteção CSRF habilitada.
        return http.build();
    }
}