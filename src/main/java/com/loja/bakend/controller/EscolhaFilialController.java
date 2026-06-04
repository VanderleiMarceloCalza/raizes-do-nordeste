package com.loja.bakend.controller;

import com.loja.bakend.model.Filial;
import com.loja.bakend.model.Pedido;
import com.loja.bakend.repository.FilialRepository;

import com.loja.bakend.repository.PedidoRepository;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EscolhaFilialController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private FilialRepository repository;

	@GetMapping("/filiais")
	public String pagina(Model model) {

		model.addAttribute("filiais", repository.findAll());

		return "filiais";
	}

	@PostMapping("/filiais")
	public String escolher(

			@RequestParam Long filialId,

			HttpSession session) {

		session.setAttribute("filialId", filialId);

		Long pedidoId =

				(Long) session.getAttribute("pedidoId");

		if (pedidoId != null) {

			Pedido pedido =

					pedidoRepository.findById(pedidoId)

							.orElseThrow();

			Filial filial =

					repository.findById(filialId)

							.orElseThrow();

			pedido.setFilial(filial);

			pedidoRepository.save(pedido);
		}

		return "redirect:/cardapio";

	}

}