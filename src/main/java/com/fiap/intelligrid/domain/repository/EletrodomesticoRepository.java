package com.fiap.intelligrid.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;

public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long>{

	long deleteEletrodomesticoById(Long id);

}
