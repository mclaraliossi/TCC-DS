package br.com.accessone.accessone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Criptografa e verifica as senhas dos usuários.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                // Páginas públicas e arquivos estáticos.
                .requestMatchers(
                    "/",
                    "/accessone",
                    "/login",
                    "/cadastro/empresa",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/img/**",
                    "/favicon.ico",
                    "/error"
                ).permitAll()

                // Somente administrador.
                .requestMatchers("/admin/**")
                    .hasRole("ADMIN")

                // Somente empresas.
                .requestMatchers(
                    "/empresa/**",
                    "/produtos/**"
                ).hasRole("EMPRESA")

                // Outras páginas exigem login.
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/redirecionar", true)
                .failureUrl("/login?error")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}