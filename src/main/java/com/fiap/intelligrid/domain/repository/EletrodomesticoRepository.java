package com.fiap.intelligrid.domain.repository;

import com.fiap.intelligrid.domain.entity.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long>{ 
    
}
