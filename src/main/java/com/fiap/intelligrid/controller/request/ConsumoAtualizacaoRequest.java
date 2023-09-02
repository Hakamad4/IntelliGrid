package com.fiap.intelligrid.controller.request;

import java.time.LocalDate;

import com.fiap.intelligrid.domain.entity.Eletrodomestico;
import com.fiap.intelligrid.domain.entity.Pessoa;

public record ConsumoAtualizacaoRequest(Long id, Eletrodomestico eletrodomestico,Pessoa pessoa,  double consumoTotal, Long tempo, LocalDate dia) {

}
