package com.loja.bakend.controller;

import com.loja.bakend.repository.FilialRepository;
import com.loja.bakend.model.Pedido;
import com.loja.bakend.model.Cliente;
import com.loja.bakend.model.Filial;

import com.loja.bakend.repository.PedidoRepository;
import com.loja.bakend.repository.ClienteRepository;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EscolhaFilialController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
    @Autowired
    private FilialRepository repository;

    @GetMapping("/filiais")
    public String pagina(Model model) {

        model.addAttribute(
            "filiais",
            repository.findAll()
        );

        return "filiais";
    }

    @PostMapping("/filiais")
    public String escolher(

            @RequestParam Long filialId,

            HttpSession session) {

        session.setAttribute(
            "filialId",
            filialId
        );
        
        Long clienteId =

                (Long) session.getAttribute(
                    "clienteId"
                );

            if(clienteId != null) {

                Cliente cliente =

                    clienteRepository.findById(
                        clienteId
                    )

                    .orElseThrow();

                Filial filial =

                    repository.findById(
                        filialId
                    )

                    .orElseThrow();

                Pedido pedido =
                    new Pedido();
                pedido.setDataPedido(
                	    LocalDateTime.now()
                	);
                pedido.setCliente(
                    cliente
                );

                pedido.setFilial(
                    filial
                );

                pedido.setStatus(
                    "PENDENTE"
                );

                pedido.setValorTotal(0.0);

                pedidoRepository.save(
                    pedido
                );

                session.setAttribute(

                    "pedidoId",

                    pedido.getId()
                );
            }

            return "redirect:/cardapio";
        }
        
      
}