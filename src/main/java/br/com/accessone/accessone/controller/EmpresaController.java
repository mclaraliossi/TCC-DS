package br.com.accessone.accessone.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.accessone.accessone.entity.Empresa;
import br.com.accessone.accessone.service.EmpresaService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // A URL final será /empresa/minha.
    @GetMapping("/minha")
    public String minhaEmpresa(Principal principal, Model model) {

        // Principal representa a conta autenticada.
        // O e-mail é obtido da sessão, e não de um campo enviado
        // pelo navegador, evitando confiar em uma identidade informada.
        Empresa empresa = empresaService.buscarMinhaEmpresa(
            principal.getName()
        );

        // Disponibiliza os dados para a página Thymeleaf.
        model.addAttribute("empresa", empresa);

        return "empresa/minha-empresa";
    }
}