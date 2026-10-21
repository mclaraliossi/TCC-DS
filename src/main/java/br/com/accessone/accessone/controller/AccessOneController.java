package br.com.accessone.accessone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller 
@RequestMapping("/accessone")
public class AccessOneController {
    
    @GetMapping
    public String index(Model model){
        return "index";
    }
}
