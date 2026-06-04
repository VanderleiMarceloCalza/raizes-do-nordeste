package com.loja.bakend.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.loja.bakend.model.Pedido;
import com.loja.bakend.repository.PedidoRepository;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoApiController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public String teste() {

        return "Pagamento API funcionando";
    }

    @PostMapping
    public Map<String, Object> pagar(

            @RequestParam Long pedidoId) {

        Pedido pedido =

            pedidoRepository.findById(
                pedidoId
            )

            .orElseThrow();

        String codigo =

            "PAG" +

            new Random().nextInt(
                999999
            );

        pedido.setStatusPagamento(
            "APROVADO"
        );

        pedido.setCodigoPagamento(
            codigo
        );

        pedido.setDataPagamento(
            LocalDateTime.now()
        );

        pedidoRepository.save(
            pedido
        );

        Map<String, Object> resposta =

            new HashMap<>();

        resposta.put(
            "pedidoId",
            pedido.getId()
        );

        resposta.put(
            "valor",
            pedido.getValorTotal()
        );

        resposta.put(
            "status",
            "APROVADO"
        );

        resposta.put(
            "codigo",
            codigo
        );

        return resposta;
    }
}