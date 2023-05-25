package com.fiap.intelligrid.domain.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoResponse {

	private String cep;

	private String logradouro;

	private String numero;

	private String bairro;

	private String cidade;

	private String estado;

	private String complemento;

}
