package com.fiap.intelligrid.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.intelligrid.domain.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

public record EnderecoRequest(
		@JsonProperty(required = true) String cep,
		String logradouro,
		String numero,
		String bairro,
		String cidade,
		String estado,
		String complemento
) {

	public Endereco toEntity() {
		return new Endereco(
				null,
				cep,
				logradouro,
				numero,
				bairro,
				cidade,
				estado,
				complemento
		);
	}
}
