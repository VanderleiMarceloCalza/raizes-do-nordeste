package com.loja.bakend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.loja.bakend.model.Cliente;

import com.loja.bakend.repository.ClienteRepository;
import com.loja.bakend.util.SenhaUtil;
import com.loja.bakend.exception.RecursoNaoEncontradoException;
@RestController

@RequestMapping("/api/clientes")

public class ClienteApiController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public List<Cliente> listar() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente buscar(

            @PathVariable Long id) {

        return repository.findById(id)

            .orElseThrow(() ->

                new RecursoNaoEncontradoException(
                    "Cliente não encontrado"
                )
            );
    }

    @PostMapping
    public Cliente salvar(

            @RequestBody Cliente cliente) {

        cliente.setSenha(

            SenhaUtil.criptografar(
                cliente.getSenha()
            )
        );

        return repository.save(
            cliente
        );
    }
    @PostMapping("/cadastro")
    public String criar(Cliente cliente) {

        cliente.setSenha(

            SenhaUtil.criptografar(
                cliente.getSenha()
            )
        );

        repository.save(cliente);

        return "redirect:/login";
    }
}
