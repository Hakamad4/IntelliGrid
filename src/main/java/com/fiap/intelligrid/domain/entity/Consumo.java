package com.fiap.intelligrid.domain.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "consumo")
@ToString
public class Consumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double potenciaTotal;
	private long tempo;
	private LocalDate dia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eletrodomestico_id")
	@JsonBackReference
	private Eletrodomestico eletrodomestico;

	@PrePersist
	private void prePersit() {
		dia = LocalDate.now();
	}

}
