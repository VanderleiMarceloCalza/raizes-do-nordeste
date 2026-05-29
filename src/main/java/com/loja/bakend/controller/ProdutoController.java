package com.loja.bakend.controller;

import com.loja.bakend.model.Estoque;
import com.loja.bakend.model.Produto;
import com.loja.bakend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import com.loja.bakend.repository.EstoqueRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private EstoqueRepository estoqueRepository;
    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @GetMapping
    public List<Estoque> listar(

            HttpSession session) {

        Long filialId =

            (Long) session.getAttribute(
                "filialId"
            );

        if(filialId == null) {

            return List.of();
        }

        return estoqueRepository.findByFilialId(
            filialId
        );
    }
  }
