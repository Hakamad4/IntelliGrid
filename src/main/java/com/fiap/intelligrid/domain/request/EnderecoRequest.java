package com.fiap.intelligrid.domain.request;

import com.fiap.intelligrid.config.validator.ValidCep;
import com.fiap.intelligrid.domain.entity.Endereco;
import jakarta.validation.constraints.NotBlank;


public record EnderecoRequest(
		@ValidCep
		String cep,
		@NotBlank
		String logradouro,
		@NotBlank
		String numero,
		@NotBlank
		String bairro,
		@NotBlank
		String cidade,
		@NotBlank
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
				complemento,
				null
		);
	}
}
