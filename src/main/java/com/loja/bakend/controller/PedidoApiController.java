package com.loja.bakend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.loja.bakend.model.CanalPedido;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loja.bakend.model.Cliente;
import com.loja.bakend.model.Pedido;
import com.loja.bakend.repository.ClienteRepository;
import com.loja.bakend.repository.PedidoRepository;

@RestController

@RequestMapping("/api/pedidos")

public class PedidoApiController {

    @Autowired
    private PedidoRepository repository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Pedido> listar(

            @RequestParam(required = false)
            CanalPedido canalPedido) {

        if (canalPedido != null) {

            return repository.findByCanalPedido(
                canalPedido
            );
        }

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido buscar(

            @PathVariable Long id) {

        return repository.findById(id)

                .orElseThrow();
    }

    @PostMapping
    public Pedido criar(

            @RequestBody Pedido pedido) {

        if (pedido.getCliente() == null
                || pedido.getCliente().getId() == null) {

            throw new RuntimeException(
                "Cliente não informado"
            );
        }

        Cliente cliente =

            clienteRepository.findById(
                pedido.getCliente().getId()
            )

            .orElseThrow(() ->

                new RuntimeException(
                    "Cliente não encontrado"
                )
            );

        pedido.setCliente(
            cliente
        );

        pedido.setStatus(
            "PENDENTE"
        );

        return repository.save(
            pedido
        );
    }
    
    @PutMapping("/{id}/status")

    public Pedido atualizarStatus(

            @PathVariable Long id,

            @RequestParam String status) {

        Pedido pedido =

            repository.findById(id)

                .orElseThrow();

        pedido.setStatus(
            status
        );

        return repository.save(
            pedido
        );
    }
    
    @PutMapping("/finalizar/{id}")
    public Pedido finalizar(

            @PathVariable Long id,

            @RequestParam(
                defaultValue = "false"
            )
            Boolean usarPontos) {

        Pedido pedido =

            repository.findById(id)
            .orElseThrow();

        if(Boolean.TRUE.equals(
                usarPontos)) {

            Cliente cliente =

                pedido.getCliente();

            if(cliente != null
               && cliente.getPontos() != null
               && cliente.getPontos() > 0) {

                double desconto =

                    cliente.getPontos()
                    * 0.10;

                double novoValor =

                    pedido.getValorTotal()
                    - desconto;

                if(novoValor < 0) {

                    novoValor = 0;
                }

                pedido.setValorTotal(
                    novoValor
                );

                cliente.setPontos(0);

                clienteRepository.save(
                    cliente
                );
            }
        }

        pedido.setStatus(
            "EM PREPARO"
        );

        return repository.save(
            pedido
        );
    }

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
}