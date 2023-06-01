package com.fiap.intelligrid.domain.repository;

import com.fiap.intelligrid.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
