package com.fiap.intelligrid.domain.response;

import com.fiap.intelligrid.domain.entity.Voltagem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EletrodomesticoResponse {

    private String nome;
    
    private String modelo;
    
    private String potencia;

    private Voltagem voltagem;
}
