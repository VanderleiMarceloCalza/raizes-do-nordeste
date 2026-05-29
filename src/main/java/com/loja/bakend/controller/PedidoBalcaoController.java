package com.loja.bakend.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loja.bakend.model.Cliente;
import com.loja.bakend.model.Filial;
import com.loja.bakend.model.Pedido;

import com.loja.bakend.repository.ClienteRepository;
import com.loja.bakend.repository.PedidoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PedidoBalcaoController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/pedido/balcao")
    public String tela(

            HttpSession session,

            Model model) {

        Long clienteId =

            (Long) session.getAttribute(
                "clienteId"
            );

        Cliente atendente =

            clienteRepository.findById(
                clienteId
            )

            .orElseThrow();

        model.addAttribute(

            "nome",

            atendente.getNome()
        );

        model.addAttribute(

            "filial",

            atendente.getFilial().getNome()
        );

        return "pedidoBalcao";
    }

    @PostMapping("/pedido/balcao")
    public String criar(

            @RequestParam String nome,

            HttpSession session) {

        Long clienteId =

            (Long) session.getAttribute(
                "clienteId"
            );

        Cliente atendente =

            clienteRepository.findById(
                clienteId
            )

            .orElseThrow();

        Filial filial =
            atendente.getFilial();

        Cliente cliente =
            new Cliente();

        cliente.setNome(
            nome + " (Balcão)"
        );

        cliente.setTipo(
            "CLIENTE"
        );

        cliente.setSenha(
            ""
        );

        clienteRepository.save(
            cliente
        );

        Pedido pedido =
            new Pedido();

        pedido.setCliente(
            cliente
        );

        pedido.setFilial(
            filial
        );

        pedido.setStatus(
            "PENDENTE"
        );

        pedido.setValorTotal(
            0.0
        );

        pedido.setDataPedido(
            LocalDateTime.now()
        );

        pedidoRepository.save(
            pedido
        );

        session.setAttribute(

            "pedidoId",

            pedido.getId()
        );
        
        session.setAttribute(

        	    "clienteBalcaoId",

        	    cliente.getId()
        	);
        
        session.setAttribute(

        	    "filialId",

        	    filial.getId()
        	);
        
        

        return "redirect:/cardapio";
    }
}
