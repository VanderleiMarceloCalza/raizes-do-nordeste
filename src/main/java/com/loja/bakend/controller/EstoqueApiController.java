package com.loja.bakend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.loja.bakend.model.Estoque;

import com.loja.bakend.repository.EstoqueRepository;

@RestController

@RequestMapping("/api/estoque")

public class EstoqueApiController {

    @Autowired
    private EstoqueRepository repository;
    
    @GetMapping("/filial/{filialId}")
    public List<Estoque> listarPorFilial(

            @PathVariable Long filialId) {

        return repository.findByFilialId(
            filialId
        );
    }
    
    @GetMapping
    public List<Estoque> listar() {

        return repository.findAll();
    }
    
    
    
    @GetMapping("/{id}")
    public Estoque buscar(

            @PathVariable Long id) {

        return repository.findById(id)

                .orElseThrow();
    }

    @PostMapping
    public Estoque salvar(

            @RequestBody Estoque estoque) {

        return repository.save(
            estoque
        );
    }

    @PutMapping("/{id}")
    public Estoque atualizar(

            @PathVariable Long id,

            @RequestBody Estoque novo) {

        Estoque estoque =

            repository.findById(id)

                .orElseThrow();

        estoque.setQuantidade(

            novo.getQuantidade()
        );

        return repository.save(
            estoque
        );
    }
}
