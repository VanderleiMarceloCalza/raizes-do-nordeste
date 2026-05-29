package com.loja.bakend.repository;

import com.loja.bakend.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilialRepository
        extends JpaRepository<Filial, Long> {
}