package com.loja.bakend.repository;

import com.loja.bakend.model.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository
    extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(
        String status
    );

    List<Pedido> findByFilialId(
        Long filialId
    );

    List<Pedido> findByClienteId(
        Long clienteId
    );
}