package br.com.accessone.accessone.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.accessone.accessone.dto.CadastroEmpresaDTO;
import br.com.accessone.accessone.service.EmpresaService;

@Controller
public class UsuarioController {

    // O controller delega o cadastro ao serviço.
    private final EmpresaService empresaService;

    public UsuarioController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // Exibe o formulário de cadastro.
    @GetMapping("/cadastro/empresa")
    public String formularioCadastro(Model model) {

        // Disponibiliza um objeto vazio para o formulário Thymeleaf.
        model.addAttribute(
            "cadastroEmpresa",
            new CadastroEmpresaDTO()
        );

        return "usuario/cadastro-empresa";
    }

    // Recebe os dados enviados pelo formulário.
    @PostMapping("/cadastro/empresa")
    public String cadastrar(
            @Valid @ModelAttribute("cadastroEmpresa")
            CadastroEmpresaDTO dto,
            BindingResult resultado,
            Model model) {

        // Se as validações falharem, retorna ao formulário.
        if (resultado.hasErrors()) {
            return "usuario/cadastro-empresa";
        }

        try {
            // Solicita ao serviço o cadastro da conta e da empresa.
            empresaService.cadastrarEmpresa(dto);

            // Após o cadastro, direciona para o login.
            return "redirect:/login?cadastro";

        } catch (IllegalArgumentException e) {
            // Mostra a mensagem de erro no formulário.
            model.addAttribute("erroCadastro", e.getMessage());

            return "usuario/cadastro-empresa";
        }
    }
}