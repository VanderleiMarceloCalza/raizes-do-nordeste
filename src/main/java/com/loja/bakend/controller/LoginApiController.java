package com.loja.bakend.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.loja.bakend.model.Cliente;

import com.loja.bakend.model.LoginRequest;

import com.loja.bakend.repository.ClienteRepository;

import com.loja.bakend.util.SenhaUtil;
@RestController

@RequestMapping("/api")

public class LoginApiController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping("/login")

    public Cliente login(

            @RequestBody LoginRequest request) {

    	Cliente cliente = repository
    		    .findByEmail(request.getEmail())
    		    .orElseThrow(() ->
    		        new RuntimeException(
    		            "Usuário não encontrado"
    		        )
    		    );

        if(cliente == null) {

            throw new RuntimeException(
                "Usuário não encontrado"
            );
        }

        if(!SenhaUtil.verificar(

                request.getSenha(),

                cliente.getSenha())) {
        	
        	throw new RuntimeException(
        	        "Senha inválida");
        }

        return cliente;
    }
}
