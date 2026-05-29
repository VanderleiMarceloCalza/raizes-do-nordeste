package com.loja.bakend.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String sair(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}