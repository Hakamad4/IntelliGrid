package com.fiap.intelligrid.controller.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.intelligrid.domain.entity.Endereco;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EnderecoResponseComEletrodomestico (
		Long id,
		String cep,
		String logradouro,
		String numero,
		String bairro,
		String cidade,
		String estado,
		String complemento,
		List<EletrodomesticoResponse> eletrodomesticos) {

	public EnderecoResponseComEletrodomestico(Endereco endereco) {
		this(endereco.getId(),
				endereco.getCep(),
				endereco.getLogradouro(),
				endereco.getNumero(),
				endereco.getBairro(),
				endereco.getCidade(),
				endereco.getEstado(),
				endereco.getComplemento(),
				endereco.getEletrodomesticos().stream().map(EletrodomesticoResponse::new).toList()
		);
	}
}
