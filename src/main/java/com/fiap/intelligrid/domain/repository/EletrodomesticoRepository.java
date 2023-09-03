package com.fiap.intelligrid.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fiap.intelligrid.domain.entity.Eletrodomestico;

public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long>{

	long deleteEletrodomesticoById(Long id);

	@Query("SELECT e FROM Eletrodomestico e WHERE " +
		"(:nome IS NULL OR e.nome = :nome) AND " +
		"(:modelo IS NULL OR e.modelo = :modelo) AND " +
		"(:potencia IS NULL OR e.potencia = :potencia)")
	List<Eletrodomestico> findByNomeModeloPotencia( @Param("nome") String nome,
										 			@Param("modelo") String modelo,
										 			@Param("potencia") String potencia);

}
