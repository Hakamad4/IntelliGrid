package com.fiap.intelligrid.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {

	@JsonProperty(required = true)
	private String cep;

	private String logradouro;

	private String numero;

	private String bairro;

	private String cidade;

	private String estado;

	private String complemento;

}
