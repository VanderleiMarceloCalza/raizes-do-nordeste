package com.loja.bakend.controller;

import com.loja.bakend.model.*;
import com.loja.bakend.repository.*;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens")
public class ItemPedidoController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ItemPedidoRepository itemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ItemPedido criar(

            @RequestBody ItemPedido item,

            HttpSession session) {

        Produto produto =
            produtoRepository.findById(
                item.getProduto().getId()
            )
            .orElseThrow(() ->

                new RuntimeException(
                    "Produto não encontrado"
                )
            );

        Pedido pedido =
            pedidoRepository.findById(
                item.getPedido().getId()
            )
            .orElseThrow(() ->

                new RuntimeException(
                    "Pedido não encontrado"
                )
            );

        Long filialId =

        	    (Long) session.getAttribute(
        	        "filialId"
        	    );

        	if(filialId == null) {

        	    filialId =

        	        pedido.getFilial()
        	              .getId();
        	}

        Estoque estoque =

            estoqueRepository

            .findByProdutoIdAndFilialId(

                produto.getId(),

                filialId
            )

            .orElseThrow(() ->

                new RuntimeException(
                    "Produto sem estoque"
                )
            );

        Optional<ItemPedido> existente =

            itemRepository.findByPedidoIdAndProdutoId(

                pedido.getId(),

                produto.getId()
            );

        // 🔥 VERIFICA ESTOQUE
        if(estoque.getQuantidade() <= 0) {

            throw new RuntimeException(
                "Produto sem estoque"
            );
        }

        // 🔥 DIMINUI ESTOQUE
        estoque.setQuantidade(

            estoque.getQuantidade() - 1
        );

        estoqueRepository.save(estoque);

        // 🔥 ITEM JÁ EXISTE
        if (existente.isPresent()) {

            ItemPedido itemExistente =
                existente.get();

            itemExistente.setQuantidade(

                itemExistente.getQuantidade() + 1
            );

            itemRepository.save(itemExistente);

            double total = 0;

            for(ItemPedido i : pedido.getItens()) {

                total +=

                    i.getQuantidade()

                    * i.getPrecoUnitario();
            }

            pedido.setValorTotal(total);

            pedidoRepository.save(pedido);

            return itemExistente;
        }

        // 🔥 NOVO ITEM
        item.setProduto(produto);

        item.setPedido(pedido);

        item.setPrecoUnitario(
            produto.getPreco()
        );

        ItemPedido novoItem =
            itemRepository.save(item);

        double total = 0;

        for(ItemPedido i : pedido.getItens()) {

            total +=

                i.getQuantidade()

                * i.getPrecoUnitario();
        }

        total +=

            novoItem.getQuantidade()

            * novoItem.getPrecoUnitario();

        pedido.setValorTotal(total);

        pedidoRepository.save(pedido);

        return novoItem;
    }

    @DeleteMapping("/{id}")
    public void remover(
            @PathVariable Long id) {

        itemRepository.deleteById(id);
    }
}