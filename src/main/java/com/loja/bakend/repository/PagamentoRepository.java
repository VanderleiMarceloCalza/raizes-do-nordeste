package com.loja.bakend.repository;

import com.loja.bakend.model.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository
        extends JpaRepository<Pagamento, Long> {
}