package com.loja.bakend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.bakend.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

Optional<ItemPedido> findByPedidoIdAndProdutoId(Long pedidoId, Long produtoId);
}