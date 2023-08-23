package com.fiap.intelligrid.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fiap.intelligrid.domain.entity.Consumo;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long>{
	long deleteConsumoById(Long id);
}
