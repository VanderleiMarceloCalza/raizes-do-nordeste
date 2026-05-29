package com.loja.bakend.controller;

import com.loja.bakend.model.Cliente;
import com.loja.bakend.model.Pedido;

import com.loja.bakend.repository.ClienteRepository;
import com.loja.bakend.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository repository;

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
    	if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            throw new RuntimeException("Cliente não informado");
        }

        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        pedido.setCliente(cliente);
        pedido.setStatus("PENDENTE");

        return repository.save(pedido);
    }
    @PutMapping("/finalizar/{id}")
    public Pedido finalizar(
            @PathVariable Long id) {

        Pedido pedido =
            repository.findById(id).orElseThrow();

        pedido.setStatus(
        	    "EM PREPARO"
        	);

        repository.save(pedido);

        Pedido novoPedido = new Pedido();

        novoPedido.setCliente(
            pedido.getCliente()
        );

        novoPedido.setStatus("PENDENTE");

        novoPedido.setValorTotal(0.0);

        return repository.save(novoPedido);
    }
    
    @GetMapping
    public List<Pedido> listar() {
        return repository.findAll();
    }
}