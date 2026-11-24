package br.com.accessone.accessone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessOneController {

    @GetMapping({"/", "/accessone"})
    public String index() {
        return "index";
    }
}