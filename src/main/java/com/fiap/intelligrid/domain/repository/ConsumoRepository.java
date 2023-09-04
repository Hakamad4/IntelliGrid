package com.fiap.intelligrid.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fiap.intelligrid.domain.entity.Consumo;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long>{
	long deleteConsumoById(Long id);
	
	@Query( "SELECT c FROM Consumo c WHERE c.dia >= ?1 AND c.dia <= ?2"  )
	List<Consumo> findByDia(LocalDate dtInicio, LocalDate dtTermino);
	
}
