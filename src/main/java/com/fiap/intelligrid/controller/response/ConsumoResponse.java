package com.fiap.intelligrid.controller.response;

import com.fiap.intelligrid.domain.entity.Consumo;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;

public record ConsumoResponse(Long id, Eletrodomestico eletrodomestico) {
	public ConsumoResponse(Consumo consumo) {
		this(consumo.getId(), consumo.getEletrodomestico());
	}
}
