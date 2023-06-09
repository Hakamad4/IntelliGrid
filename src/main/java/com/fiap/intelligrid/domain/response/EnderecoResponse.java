package com.fiap.intelligrid.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor
public class EnderecoResponse {

	private String cep;

	private String logradouro;

	private String numero;

	private String bairro;

	private String cidade;

	private String estado;

	private String complemento;

}
