package com.fiap.intelligrid.controller.response;

import java.time.LocalDate;
import com.fiap.intelligrid.domain.entity.Consumo;

public record ConsumoResponse(Long id, long tempo, LocalDate dia, long idEletrodomestico, String nomeEletrodomestico) {
	public ConsumoResponse(Consumo consumo) {
		this(consumo.getId(), consumo.getTempo(), consumo.getDia(), consumo.getEletrodomestico().getId(), consumo.getEletrodomestico().getNome());
	}
}
