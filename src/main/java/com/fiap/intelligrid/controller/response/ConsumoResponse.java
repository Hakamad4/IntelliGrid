package com.fiap.intelligrid.controller.response;

import java.time.LocalDate;

import com.fiap.intelligrid.domain.entity.Consumo;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;

public record ConsumoResponse(Long id, Eletrodomestico eletrodomestico,  double potenciaTotal, Long tempo,LocalDate dia) {
	public ConsumoResponse(Consumo consumo) {
		this(consumo.getId(), consumo.getEletrodomestico(), consumo.getPotenciaTotal(), consumo.getTempo(), consumo.getDia());
	}
}
