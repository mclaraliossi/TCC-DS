package br.com.accessone.accessone.controller;

import java.security.Principal;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.accessone.accessone.entity.Produto;
import br.com.accessone.accessone.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Lista somente produtos da empresa autenticada.
    @GetMapping("/listar")
    public String listar(
            Principal principal,
            Model model) {

        model.addAttribute(
            "produtos",
            produtoService.listarDaEmpresa(
                principal.getName()
            )
        );

        return "produto/listarProduto";
    }

    // Exibe o formulário de novo produto.
    @GetMapping("/criar")
    public String criar(Model model) {

        model.addAttribute(
            "produto",
            new Produto()
        );

        return "produto/formularioProduto";
    }

    // Abre a edição de produto da empresa.
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Principal principal,
            Model model) {

        model.addAttribute(
            "produto",
            produtoService.buscarDaEmpresa(
                id,
                principal.getName()
            )
        );

        return "produto/formularioProduto";
    }

    // Salva ou atualiza um produto.
    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("produto")
            Produto produto,

            BindingResult resultado,
            Principal principal) {

        if (resultado.hasErrors()) {
            return "produto/formularioProduto";
        }

        produtoService.salvarParaEmpresa(
            produto,
            principal.getName()
        );

        return "redirect:/produtos/listar?salvo";
    }

    // Exclusão via POST, protegida por CSRF.
    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            Principal principal) {

        produtoService.excluirDaEmpresa(
            id,
            principal.getName()
        );

        return "redirect:/produtos/listar?excluido";
    }
}