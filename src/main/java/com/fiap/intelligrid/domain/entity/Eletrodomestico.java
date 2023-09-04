package com.fiap.intelligrid.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fiap.intelligrid.controller.request.EletrodomesticoRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Eletrodomestico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String modelo;
	private double potencia;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "eletrodomestico" , fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Consumo> consumos = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

	public Eletrodomestico(EletrodomesticoRequest eletroeletronicoRequest) {
		this.nome = eletroeletronicoRequest.nome();
		this.modelo = eletroeletronicoRequest.modelo();
		this.potencia = eletroeletronicoRequest.potencia();
	}
}
