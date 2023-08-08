package com.fiap.intelligrid.controller.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fiap.intelligrid.domain.entity.Eletrodomestico;
import com.fiap.intelligrid.domain.entity.Pessoa;

public record ConsumoAtualizacaoRequest(Long id, Eletrodomestico eletrodomestico,Pessoa pessoa,  double potenciaTotal, LocalDateTime tempo, LocalDate dia) {

}
