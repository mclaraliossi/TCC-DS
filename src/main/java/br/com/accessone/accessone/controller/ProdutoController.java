package br.com.accessone.accessone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        //Método para salvar produto
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto){
        produtoService.save(produto);
        return "redirect:/produtos/listar";
    }

    //Método paa listar todos os produtos
    @GetMapping("/listar")
    public String listar(Model model){
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        return "produto/listarProduto";
    }

    //Método para exibir o formulário de cadastro de produto
    @GetMapping("/criar")
    public String criar(Model model){
        model.addAttribute("produto", new Produto());
        return "produto/formularioProduto";
    }
    // Método para excluir cliente
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        produtoService.deleteById(id);
        return "redirect:/produtos/listar";
    }

    // Método para exibir o formulário de edição do cliente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = produtoService.findById(id);
        model.addAttribute("produto", produto);
        return "produto/formularioProduto";
    }
}
