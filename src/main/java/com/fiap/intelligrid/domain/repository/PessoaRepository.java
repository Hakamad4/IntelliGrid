package com.fiap.intelligrid.domain.repository;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.entity.enums.Genero;

import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	long deletePessoaById(Long id);


    @Query("SELECT p FROM Pessoa p WHERE " +
            "(:nome IS NULL OR p.nome = :nome) AND " +
            "(:genero IS NULL OR p.genero = :genero)")
    List<Pessoa> findByNomeSexo(@Param("nome") String nome, @Param("genero") Genero genero);
}
