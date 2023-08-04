package com.fiap.intelligrid.controller.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fiap.intelligrid.domain.entity.Consumo;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;
import com.fiap.intelligrid.domain.entity.Pessoa;

public record ConsumoResponse(Long id, Eletrodomestico eletrodomestico,Pessoa pessoa,  double potenciaTotal, LocalDateTime tempo, LocalDate dia) {

	public ConsumoResponse(Consumo consumo) {
		this(consumo.getId(), null, null, consumo.getPotenciaTotal(), consumo.getTempo(), consumo.getDia());
	}
	
	
	
	
}
