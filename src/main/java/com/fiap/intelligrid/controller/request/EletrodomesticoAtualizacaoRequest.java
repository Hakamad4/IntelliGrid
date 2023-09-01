package com.fiap.intelligrid.controller.request;

import java.util.List;

import com.fiap.intelligrid.domain.entity.Consumo;

public record EletrodomesticoAtualizacaoRequest(String nome , String modelo, double potencia, List<Consumo> consumos) {

}
