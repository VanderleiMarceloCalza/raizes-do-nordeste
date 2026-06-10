package com.loja.bakend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.loja.bakend.model.Cliente;
import com.loja.bakend.repository.ClienteRepository;
import com.loja.bakend.repository.FilialRepository;
import com.loja.bakend.util.SenhaUtil;

@Controller
public class CadastroController {
	
	@Autowired
	private FilialRepository filialRepository;
	
    @Autowired
    private ClienteRepository repository;

    @GetMapping("/cadastro")
    public String pagina(Model model) {

        model.addAttribute(

            "filiais",

            filialRepository.findAll()
        );

        return "cadastro";
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