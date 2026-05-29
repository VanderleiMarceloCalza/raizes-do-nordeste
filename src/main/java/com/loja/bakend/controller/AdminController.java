package com.loja.bakend.controller;

import com.loja.bakend.model.Estoque;
import com.loja.bakend.model.Cliente;
import com.loja.bakend.model.Filial;
import com.loja.bakend.model.Produto;
import com.loja.bakend.repository.ClienteRepository;
import com.loja.bakend.repository.EstoqueRepository;
import com.loja.bakend.repository.FilialRepository;
import com.loja.bakend.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;
    
    @GetMapping("/admin")
    public String admin(Model model) {
    	model.addAttribute(

    		    "filiais",

    		    filialRepository.findAll()
    		);
        model.addAttribute(
            "produtos",
            repository.findAll()
        );
        model.addAttribute(

        	    "estoques",

        	    estoqueRepository.findAll()
        	);
        return "admin";
    }

    @PostMapping("/admin/produto")
    public String salvar(

            Produto produto) {

        Produto novoProduto =
            repository.save(produto);

        for(Filial filial :
            filialRepository.findAll()) {

            Estoque estoque =
                new Estoque();

            estoque.setProduto(
                novoProduto
            );

            estoque.setFilial(
                filial
            );

            estoque.setQuantidade(0);

            estoqueRepository.save(
                estoque
            );
        }

        return "redirect:/admin";
    }
    @PostMapping("/admin/estoque")
    
    public String reporEstoque(

            @RequestParam Long id,

            @RequestParam Integer quantidade) {

        Estoque estoque =

            estoqueRepository.findById(id)

            .orElseThrow();

        estoque.setQuantidade(

            estoque.getQuantidade()
            + quantidade
        );

        estoqueRepository.save(estoque);

        return "redirect:/admin";
    }
    @GetMapping("/admin/excluir/{id}")
    public String excluir(@PathVariable Long id) {
    	estoqueRepository.deleteByProdutoId(id);
        repository.deleteById(id);

        return "redirect:/admin";
    }
    
    @PostMapping("/admin/funcionario")
    public String criarFuncionario(

            Cliente cliente,

            @RequestParam(required = false)
            Long filialId) {

        if(filialId != null) {

            Filial filial =

                filialRepository.findById(
                    filialId
                )

                .orElseThrow();

            cliente.setFilial(
                filial
            );
        }

        clienteRepository.save(cliente);

        return "redirect:/admin";
    }
    
    
    
}