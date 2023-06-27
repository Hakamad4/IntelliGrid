package com.fiap.intelligrid.domain.response;

import com.fiap.intelligrid.domain.entity.Endereco;

public record EnderecoResponse(String cep,
							   String logradouro,
							   String numero,
							   String bairro,
							   String cidade,
							   String estado,
							   String complemento) {

	public EnderecoResponse(Endereco endereco) {
		this(endereco.getCep(),
				endereco.getLogradouro(),
				endereco.getNumero(),
				endereco.getBairro(),
				endereco.getCidade(),
				endereco.getEstado(),
				endereco.getComplemento()
		);
	}
}
