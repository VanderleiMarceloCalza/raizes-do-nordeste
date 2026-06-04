package com.loja.bakend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.loja.bakend.model.Produto;
import com.loja.bakend.repository.ProdutoRepository;

@RestController

@RequestMapping("/api/produtos")

public class ProdutoApiController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> listar() {

        return repository.findAll();
    }
    
    @PostMapping
    public Produto salvar(

            @RequestBody Produto produto) {

        return repository.save(
            produto
        );
    }
    
    
    
}
