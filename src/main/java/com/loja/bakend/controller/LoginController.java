package com.loja.bakend.controller;

import com.loja.bakend.util.SenhaUtil;
import com.loja.bakend.model.Cliente;
import com.loja.bakend.repository.ClienteRepository;
import com.loja.bakend.repository.PedidoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@PostMapping("/login")
	public String entrar(

			@RequestParam String email,

			@RequestParam String senha,

			HttpSession session, Model model) {

		Cliente cliente =

				clienteRepository.findByEmail(email).orElse(null);

		if (cliente == null) {

			model.addAttribute(

					"erro",

					"Email ou senha inválidos");

			return "login";
		}

		if (!SenhaUtil.verificar(

				senha,

				cliente.getSenha())) {

			model.addAttribute(

					"erro",

					"Email ou senha inválidos");

			return "login";
		}

		session.setAttribute("clienteId", cliente.getId());

		session.setAttribute("nome", cliente.getNome());

		session.removeAttribute("filialId");

		if (cliente.getTipo().equals("ADMIN")) {

			return "redirect:/admin";
		}

		if (cliente.getTipo().equals("GERENTE")) {

			return "redirect:/gerente";
		}
		if (cliente.getTipo().equals("ATENDENTE")) {

			return "redirect:/pedido/balcao";
		}

		return "redirect:/cardapio";
	}

	public PedidoRepository getPedidoRepository() {
		return pedidoRepository;
	}

	public void setPedidoRepository(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
}