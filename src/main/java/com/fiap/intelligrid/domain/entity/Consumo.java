package com.fiap.intelligrid.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fiap.intelligrid.controller.request.ConsumoRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Consumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private Eletrodomestico eletrodomestico;
	//private Pessoa pessoa;
	private double potenciaTotal;
	private LocalDateTime tempo = LocalDateTime.now();
	private LocalDate dia = LocalDate.now();
	
	public Consumo(Long id, double potenciaTotal, LocalDateTime tempo, LocalDate dia) {
		this.id = id;
		this.potenciaTotal = potenciaTotal;
		this.tempo = tempo;
		this.dia = dia;
	}
	
	
	public Consumo(ConsumoRequest consumoRequest) {
		this.potenciaTotal = consumoRequest.potenciaTotal();
	}
}
