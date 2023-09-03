package com.fiap.intelligrid.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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

	@ManyToMany(mappedBy = "enderecos", cascade = CascadeType.MERGE)
	private List<Pessoa> pessoas = new ArrayList<>();

	@OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL)
    private List<Eletrodomestico> eletrodomesticos = new ArrayList<>();

	public void addEletrodomestico(Eletrodomestico eletrodomestico) {
        this.eletrodomesticos.add(eletrodomestico);
        eletrodomestico.setEndereco(this);
    }

	public void removerEletrodomestico(Eletrodomestico eletrodomestico) {
		this.eletrodomesticos.remove(eletrodomestico);
		eletrodomestico.setEndereco(null);
	}

}
