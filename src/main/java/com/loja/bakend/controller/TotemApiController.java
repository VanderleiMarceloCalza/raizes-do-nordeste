package com.loja.bakend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.loja.bakend.model.CanalPedido;
import com.loja.bakend.model.Cliente;
import com.loja.bakend.model.Estoque;
import com.loja.bakend.model.Filial;
import com.loja.bakend.model.Pedido;
import com.loja.bakend.repository.ClienteRepository;
import com.loja.bakend.repository.EstoqueRepository;
import com.loja.bakend.repository.FilialRepository;
import com.loja.bakend.repository.PedidoRepository;

@RestController
@RequestMapping("/api/totem")
public class TotemApiController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping("/produtos")
    public List<Estoque> listarProdutos() {

        return estoqueRepository.findByFilialId(1L);
    }

    @PostMapping("/pedido")
    public Pedido criarPedido() {

        Cliente cliente =
            clienteRepository.findById(20L)
            .orElseThrow();

        Filial filial =
            filialRepository.findById(1L)
            .orElseThrow();

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setFilial(filial);
        pedido.setCanalPedido(CanalPedido.TOTEM);
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(0.0);

        return pedidoRepository.save(pedido);
    }

    @GetMapping("/pedido/{id}")
    public Pedido buscarPedido(
            @PathVariable Long id) {

        return pedidoRepository.findById(id)
                .orElseThrow();
    }

    @PutMapping("/pedido/{id}/finalizar")
    public Pedido finalizarPedido(
            @PathVariable Long id) {

        Pedido pedido =
            pedidoRepository.findById(id)
            .orElseThrow();

        pedido.setStatus("EM PREPARO");

        return pedidoRepository.save(pedido);
    }
}