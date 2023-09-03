package com.fiap.intelligrid.controller.request;

import java.util.List;

import com.fiap.intelligrid.domain.entity.Consumo;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//to json
public record EletrodomesticoRequest(
		   
		    Long id,
			@NotBlank
			String nome,
			@NotBlank
			String modelo,
			@NotNull
			double potencia,
			List<Consumo> consumos) {

		public Eletrodomestico toEntity() {
			return new Eletrodomestico(
					null,
					nome,
					modelo,
					potencia,
					consumos
					
			);
		}
}
