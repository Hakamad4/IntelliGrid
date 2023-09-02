package com.fiap.intelligrid.controller.response;

import java.util.List;

import com.fiap.intelligrid.domain.entity.Consumo;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;


public record EletrodomesticoResponse (Long id, String nome, String modelo, double potencia) {
    public EletrodomesticoResponse(Eletrodomestico eletrodomestico) {
        this(eletrodomestico.getId(), eletrodomestico.getNome(), eletrodomestico.getModelo(), eletrodomestico.getPotencia());
    }
}
