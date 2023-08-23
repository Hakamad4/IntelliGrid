package com.fiap.intelligrid.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fiap.intelligrid.controller.request.ConsumoRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "consumo")
public class Consumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double potenciaTotal;
	private LocalDateTime tempo;
	private LocalDate dia ;
	
//	@Autowired
//	private Eletrodomestico eletrodomestico;
//	@Autowired
//	private Pessoa pessoa;
	
	public Consumo(Long id, double potenciaTotal) {
		this.id = id;
		this.potenciaTotal = potenciaTotal;
	}
	
	public Consumo(ConsumoRequest consumoRequest) {
		this.potenciaTotal = consumoRequest.potenciaTotal();
	}
}
