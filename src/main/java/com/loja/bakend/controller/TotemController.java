package com.loja.bakend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TotemController {

    @GetMapping("/totem")
    public String pagina() {

        return "totem";
    }
}
