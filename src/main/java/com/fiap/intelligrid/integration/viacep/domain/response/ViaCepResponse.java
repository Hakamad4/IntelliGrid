package com.fiap.intelligrid.integration.viacep.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.intelligrid.controller.response.EnderecoResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViaCepResponse {

	private String cep;

	private String logradouro;

	private String complemento;

	private String bairro;

	@JsonProperty("localidade")
	private String cidade;

	@JsonProperty("uf")
	private String estado;

	private String ibge;

	private String gia;

	private String ddd;

	private String siafi;

	public EnderecoResponse toEnderecoResponse() {
		return new EnderecoResponse(
				null,
				cep,
				logradouro,
				complemento,
				bairro,
				cidade,
				estado,
				complemento
		);
	}

}
