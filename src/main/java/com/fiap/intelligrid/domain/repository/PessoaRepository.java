package com.fiap.intelligrid.domain.repository;

import com.fiap.intelligrid.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	long deletePessoaById(Long id);


}
