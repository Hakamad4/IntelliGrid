package com.fiap.intelligrid.controller.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fiap.intelligrid.domain.entity.Consumo;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;
import com.fiap.intelligrid.domain.entity.Pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsumoRequest(
		
		Long id,
		@NotBlank
		Eletrodomestico eletrodomestico,
		@NotBlank
		Pessoa pessoa,
		@NotNull
		double potenciaTotal,
		@NotBlank
		LocalDateTime tempo,
		@NotBlank
		LocalDate dia		
		) {

	public Consumo toEntity() {
		return new Consumo(
				null,
				potenciaTotal,
				tempo,
				dia);
	}
	
	
	
	
	
}
