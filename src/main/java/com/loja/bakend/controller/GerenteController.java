package com.loja.bakend.controller;

import com.loja.bakend.model.Cliente;
import com.loja.bakend.model.Pedido;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.loja.bakend.repository.ClienteRepository;
import com.loja.bakend.repository.EstoqueRepository;
import com.loja.bakend.repository.PedidoRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GerenteController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@GetMapping("/gerente")
	public String painel(

			HttpSession session,

			Model model) {

		Long clienteId =

				(Long) session.getAttribute("clienteId");

		Cliente gerente =

				clienteRepository.findById(clienteId)

						.orElseThrow();

		if (gerente.getFilial() == null) {

			model.addAttribute(

					"erro",

					"Gerente sem filial");

			return "gerente";
		}

		Long filialId =

				gerente.getFilial().getId();

		model.addAttribute(

				"nome",

				gerente.getNome());

		model.addAttribute(

				"filial",

				gerente.getFilial().getNome());

		model.addAttribute(

				"pedidos",

				pedidoRepository.findByFilialId(filialId));

		model.addAttribute(

				"estoques",

				estoqueRepository.findByFilialId(filialId));

		return "gerente";
	}
	@PostMapping("/gerente/status")
	public String atualizarStatus(

	        @RequestParam Long pedidoId,

	        @RequestParam String status) {

	    Pedido pedido =

	        pedidoRepository.findById(
	            pedidoId
	        )

	        .orElseThrow();

	    pedido.setStatus(
	        status
	    );

	    pedidoRepository.save(
	        pedido
	    );

	    return "redirect:/gerente";
	}
}