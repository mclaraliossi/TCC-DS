package br.com.accessone.accessone.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Exibe a tela de login compartilhada entre ADMIN e EMPRESA.
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Analisa o tipo de conta depois que o login é concluído.
    @GetMapping("/redirecionar")
    public String redirecionar(Authentication authentication) {

        // Se a conta tiver a permissão de administrador,
        // encaminha para o painel administrativo.
        if (authentication.getAuthorities().stream()
                .anyMatch(a ->
                    a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/painel";
        }

        // Caso contrário, encaminha para a área da empresa.
        return "redirect:/empresa/minha";
    }
}