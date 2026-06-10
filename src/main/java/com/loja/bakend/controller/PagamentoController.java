package com.loja.bakend.controller;

import com.loja.bakend.model.Cliente;
import com.loja.bakend.model.Pagamento;
import com.loja.bakend.model.Pedido;
import com.loja.bakend.service.PagamentoService;

import jakarta.servlet.http.HttpSession;

import com.loja.bakend.repository.PagamentoRepository;
import com.loja.bakend.repository.PedidoRepository;
import com.loja.bakend.repository.ClienteRepository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
public class PagamentoController {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoService service;

	@GetMapping("/pagamento/{id}")
	public String paginaPagamento(@PathVariable Long id, Model model) {

		model.addAttribute("pedidoId", id);
		Pedido pedido = pedidoRepository.findById(id).orElseThrow();

		model.addAttribute("valor", pedido.getValorTotal());
		return "pagamento";
	}

	@PostMapping("/pagamento")
	public String pagar(@RequestParam String metodo, @RequestParam Long pedidoId, HttpSession session, Model model) {
		
		System.out.println(
			    "Método recebido: " + metodo
			);
		
		Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();

		Pagamento pagamento = new Pagamento();

		pagamento.setMetodo(metodo);

		Map<String, String> retorno =
			    service.processarPagamento();

			if("MOCK_RECUSADO".equals(metodo)) {
				
				System.out.println("FORÇANDO RECUSA");

			    retorno.put(
			        "status",
			        "RECUSADO"
			    );

			    retorno.put(
			        "mensagem",
			        "Pagamento recusado pelo gateway mock."
			    );
			}
		
			
		pagamento.setStatus(retorno.get("status"));
		
		if("APROVADO".equals(
		        retorno.get("status"))) {
			
			
			
		    pedido.setStatus(
		    		"EM PREPARO"
		    );

		} else {

		    pedido.setStatus(
		        "CANCELADO"
		    );
		}

		pagamento.setPedido(pedido);

		pagamentoRepository.save(pagamento);
		
		pedido.setStatusPagamento(
			    retorno.get("status")
			);

			pedido.setCodigoPagamento(
			    retorno.get("codigo")
			);

			pedido.setDataPagamento(
			    java.time.LocalDateTime.now()
			);

			pedidoRepository.save(
			    pedido
			);
			
			if("APROVADO".equals(
			        retorno.get("status"))) {

			    Cliente cliente =
			        pedido.getCliente();

			    if(Boolean.TRUE.equals(
			            cliente.getParticipaFidelidade())) {

			        cliente.setPontos(

			            cliente.getPontos()

			            + pedido.getValorTotal()
			                     .intValue()
			        );

			        clienteRepository.save(
			            cliente
			        );
			    }
			}
			
		session.removeAttribute("pedidoId");
		session.setAttribute("statusPagamento", retorno.get("status"));

		session.setAttribute("codigoPagamento", retorno.get("codigo"));

		session.setAttribute("mensagemPagamento", retorno.get("mensagem"));

		model.addAttribute("metodo", metodo);

		model.addAttribute(
		    "status",
		    retorno.get("status")
		);

		model.addAttribute(
		    "codigo",
		    retorno.get("codigo")
		);

		model.addAttribute(
		    "valor",
		    pedido.getValorTotal()
		);

		if("RECUSADO".equals(
		        retorno.get("status"))) {

		    model.addAttribute(
		        "mensagem",
		        retorno.get("mensagem")
		    );

		    model.addAttribute(
		        "pedidoId",
		        pedido.getId()
		    );

		    return "pagamento-recusado";
		}

		
		return "confirmacao";

	}

	public PagamentoService getService() {
		return service;
	}

	public void setService(PagamentoService service) {
		this.service = service;
	}
}
