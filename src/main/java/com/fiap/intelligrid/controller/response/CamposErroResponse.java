package com.fiap.intelligrid.controller.response;

import org.springframework.validation.FieldError;

public record CamposErroResponse(String campo, String mensagem) {
	public CamposErroResponse(FieldError erro) {
		this(erro.getField(), erro.getDefaultMessage());
	}
}