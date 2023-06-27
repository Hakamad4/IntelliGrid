package com.fiap.intelligrid.domain.response;

public record EnderecoResponse(String cep,
							   String logradouro,
							   String numero,
							   String bairro,
							   String cidade,
							   String estado,
							   String complemento) {

}
