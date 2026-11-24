package br.com.accessone.accessone.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    private EmpresaService empresaService;

    // Mostra a página de cadastro.
    @GetMapping("/cadastro/empresa")
    public String formularioCadastro(Model model) {

        model.addAttribute(
            "cadastroEmpresa",
            new CadastroEmpresaDTO()
        );

        return "formularioUsuario";
    }

    // Recebe os dados do formulário.
    @PostMapping("/cadastro/empresa")
    public String cadastrar(
            @Valid
            @ModelAttribute("cadastroEmpresa")
            CadastroEmpresaDTO dto,

            BindingResult resultado,
            Model model) {

        if (resultado.hasErrors()) {
            return "formularioUsuario";
        }

        try {

            empresaService.cadastrarEmpresa(dto);

            return "redirect:/login?cadastro";

        } catch (IllegalArgumentException e) {

            model.addAttribute(
                "erroCadastro",
                e.getMessage()
            );

        } catch (DataIntegrityViolationException e) {

            model.addAttribute(
                "erroCadastro",
                "E-mail ou CNPJ já cadastrado."
            );
        }

        return "formularioUsuario";
    }
}