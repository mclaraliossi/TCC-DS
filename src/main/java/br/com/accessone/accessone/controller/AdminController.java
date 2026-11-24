package br.com.accessone.accessone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.accessone.accessone.repository.ProdutoRepository;
import br.com.accessone.accessone.repository.UsuarioRepository;
import br.com.accessone.accessone.service.EmpresaService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/painel")
    public String painel(Model model) {

        model.addAttribute(
            "empresas",
            empresaService.listarTodas()
        );

        model.addAttribute(
            "totalProdutos",
            produtoRepository.count()
        );

        model.addAttribute(
            "totalUsuarios",
            usuarioRepository.count()
        );

        return "admin/painel";
    }
}