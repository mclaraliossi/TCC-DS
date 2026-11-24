package br.com.accessone.accessone.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.accessone.accessone.entity.RecursoAcessibilidade;

import br.com.accessone.accessone.service.AvaliacaoService;
import br.com.accessone.accessone.service.EmpresaService;
import br.com.accessone.accessone.service.ProdutoService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    // Painel da empresa autenticada.
    @GetMapping("/minha")
    public String minhaEmpresa(
            Principal principal,
            Model model) {

        model.addAttribute(
            "empresa",
            empresaService.buscarMinhaEmpresa(
                principal.getName()
            )
        );

        model.addAttribute(
            "quantidadeProdutos",
            produtoService.contarDaEmpresa(
                principal.getName()
            )
        );

        return "homeEmpresa";
    }

    // Formulário dos recursos de acessibilidade.
    @GetMapping("/acessibilidade")
    public String formularioAcessibilidade(
            Principal principal,
            Model model) {

        model.addAttribute(
            "empresa",
            empresaService.buscarMinhaEmpresa(
                principal.getName()
            )
        );

        model.addAttribute(
            "recursosDisponiveis",
            RecursoAcessibilidade.values()
        );

        return "empresa/acessibilidade";
    }

    // Salva as opções selecionadas pela empresa.
    @PostMapping("/acessibilidade")
    public String salvarAcessibilidade(
            Principal principal,

            @RequestParam(
                value = "recursos",
                required = false
            )
            List<RecursoAcessibilidade> recursos) {

        empresaService.atualizarRecursos(
            principal.getName(),
            recursos
        );

        return "redirect:/empresa/acessibilidade?salvo";
    }

    // Lista as avaliações da empresa.
    @GetMapping("/avaliacoes")
    public String avaliacoes(
            Principal principal,
            Model model) {

        model.addAttribute(
            "avaliacoes",
            avaliacaoService.listarDaMinhaEmpresa(
                principal.getName()
            )
        );

        return "empresa/avaliacoes";
    }
}