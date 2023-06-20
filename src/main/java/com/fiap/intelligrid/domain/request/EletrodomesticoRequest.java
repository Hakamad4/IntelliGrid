package com.fiap.intelligrid.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.intelligrid.domain.entity.Voltagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EletrodomesticoRequest {
    @NotBlank
    @JsonProperty(required = true)
    private String nome;
    
    @NotBlank
    @JsonProperty(required = true)
    private String modelo;
    
    @NotNull
    @JsonProperty(required = true)
    private Double potencia;

    @NotNull
    @JsonProperty(required = true)
    private Voltagem voltagem;
}
