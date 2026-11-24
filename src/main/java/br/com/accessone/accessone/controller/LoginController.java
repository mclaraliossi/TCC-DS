package br.com.accessone.accessone.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/redirecionar")
    public String redirecionar(Authentication authentication) {

        boolean admin = authentication
            .getAuthorities()
            .stream()
            .anyMatch(a ->
                a.getAuthority().equals("ROLE_ADMIN")
            );

        if (admin) {
            return "redirect:/admin/painel";
        }

        return "redirect:/empresa/minha";
    }
}