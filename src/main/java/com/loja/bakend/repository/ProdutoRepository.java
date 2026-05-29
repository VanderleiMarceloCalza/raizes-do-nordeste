package com.loja.bakend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.bakend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}