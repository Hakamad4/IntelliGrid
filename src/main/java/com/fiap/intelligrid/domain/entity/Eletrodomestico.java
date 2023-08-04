package com.fiap.intelligrid.domain.entity;


import com.fiap.intelligrid.controller.request.EletrodomesticoRequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eletrodomestico")
public class Eletrodomestico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String modelo;
	private String potencia;

	public Eletrodomestico(EletrodomesticoRequest eletroeletronicoRequest) {
		this.nome = eletroeletronicoRequest.nome();
		this.modelo = eletroeletronicoRequest.modelo();
		this.potencia = eletroeletronicoRequest.potencia();
	}
}
