package com.fiap.intelligrid.domain.request;

import com.fiap.intelligrid.domain.entity.Eletrodomestico;

import jakarta.validation.constraints.NotBlank;

//to json
public record EletrodomesticoRequest(
		   
		    Long id,
			@NotBlank
			String nome,
			@NotBlank
			String modelo,
			@NotBlank
			String potencia) {

		public Eletrodomestico toEntity() {
			return new Eletrodomestico(
					null,
					nome,
					modelo,
					potencia
					
			);
		}
		
		
}
