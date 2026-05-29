package com.loja.bakend.repository;

import com.loja.bakend.model.Estoque;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	List<Estoque> findByFilialId(Long filialId);

	Optional<Estoque> findByProdutoIdAndFilialId(Long produtoId, Long filialId);

	@Transactional
	@Modifying
	void deleteByProdutoId(Long produtoId);
}