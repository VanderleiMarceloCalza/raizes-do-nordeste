package com.loja.bakend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.loja.bakend.repository.PedidoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AcompanhamentoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/acompanhar")
    public String acompanhar(

            HttpSession session,

            Model model) {

        Long clienteId =

            (Long) session.getAttribute(
                "clienteBalcaoId"
            );

        if(clienteId == null) {

            clienteId =

                (Long) session.getAttribute(
                    "clienteId"
                );
        }

        model.addAttribute(

            "pedidos",

            pedidoRepository.findByClienteId(
                clienteId
            )
        );

        return "acompanhar";
    }
}