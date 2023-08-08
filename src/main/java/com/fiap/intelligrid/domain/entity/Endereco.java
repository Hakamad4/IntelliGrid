package com.fiap.intelligrid.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cep", nullable = false)
	private String cep;

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "numero")
	private String numero;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "estado")
	private String estado;

	@Column(name = "complemento")
	private String complemento;

	@JoinColumn(name = "pessoa_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pessoa pessoa;

}



