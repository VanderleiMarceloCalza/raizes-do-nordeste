package com.loja.bakend.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import com.loja.bakend.model.Cliente;
import com.loja.bakend.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public String criar(Cliente cliente) {

        repository.save(cliente);

        return "redirect:/login";
    }

    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }
}