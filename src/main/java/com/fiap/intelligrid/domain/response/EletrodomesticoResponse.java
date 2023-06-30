package com.fiap.intelligrid.domain.response;

import com.fiap.intelligrid.domain.entity.Eletrodomestico;

public record EletrodomesticoResponse (Long id, String nome, String modelo, String potencia) {
    public EletrodomesticoResponse(Eletrodomestico eletrodomestico) {
        this(eletrodomestico.getId(), eletrodomestico.getNome(), eletrodomestico.getModelo(), eletrodomestico.getPotencia());
    }
}
