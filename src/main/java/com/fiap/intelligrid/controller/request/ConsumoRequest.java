package com.fiap.intelligrid.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;


public record ConsumoRequest(
		
		@JsonProperty("eletrodomestico_id")
		Long eletrodomesticoId,
		
		@NotNull
		@JsonProperty("tempo_in_ms")
        Long tempo
	
		) {

	
	
}
