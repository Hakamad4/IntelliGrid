package com.fiap.intelligrid.controller.request;

import com.fiap.intelligrid.domain.entity.Consumo;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;
import com.fiap.intelligrid.domain.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ConsumoRequest(
		
		Long id,
//		@NotBlank
//		Eletrodomestico eletrodomestico,
//		@NotBlank
//		Pessoa pessoa,
		@Positive
		double potenciaTotal
//		@NotNull
//		LocalDateTime tempo,
//		@NotNull
//		LocalDate dia		
		) {

	public Consumo toEntity() {
		return new Consumo(
				null,
				potenciaTotal);
	}
	
	
	
	
	
}
