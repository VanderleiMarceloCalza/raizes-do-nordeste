package com.loja.bakend.repository;

import com.loja.bakend.model.Pedido;
import com.loja.bakend.model.CanalPedido;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository
    extends JpaRepository<Pedido, Long> {
	
	List<Pedido> findByCanalPedido(
	        CanalPedido canalPedido
	);
	
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