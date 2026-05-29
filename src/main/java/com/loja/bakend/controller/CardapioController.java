package com.loja.bakend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.loja.bakend.repository.EstoqueRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CardapioController {
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	
	@GetMapping("/cardapio")
	public String cardapio(

	        HttpSession session,

	        Model model) {

	    Long filialId =

	        (Long) session.getAttribute(
	            "filialId"
	        );

	    if(filialId == null) {

	        return "redirect:/filiais";
	    }

	    model.addAttribute(

	        "estoques",

	        estoqueRepository.findByFilialId(
	            filialId
	        )
	    );

	    model.addAttribute(

	        "pedidoId",

	        session.getAttribute("pedidoId")
	    );

	    model.addAttribute(

	        "nome",

	        session.getAttribute("nome")
	    );

	    return "cardapio";
	}
}