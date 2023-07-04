package com.fiap.intelligrid.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.intelligrid.domain.entity.Endereco;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EnderecoResponse(
		Long id,
		String cep,
		String logradouro,
		String numero,
		String bairro,
		String cidade,
		String estado,
		String complemento) {

	public EnderecoResponse(Endereco endereco) {
		this(endereco.getId(),
				endereco.getCep(),
				endereco.getLogradouro(),
				endereco.getNumero(),
				endereco.getBairro(),
				endereco.getCidade(),
				endereco.getEstado(),
				endereco.getComplemento()
		);
	}
}
