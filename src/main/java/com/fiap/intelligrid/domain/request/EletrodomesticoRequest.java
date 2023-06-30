package com.fiap.intelligrid.domain.request;

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

		public EletrodomesticoRequest toEntity() {
			return new EletrodomesticoRequest(
					id,
					nome,
					modelo,
					potencia
					
			);
		}
}
