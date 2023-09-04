package com.fiap.intelligrid.exceptions;

import com.fiap.intelligrid.controller.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class EnderecoForbiddenException extends DefaultException {

	public EnderecoForbiddenException() {
		this("Forbidden - Endereco");
	}

	public EnderecoForbiddenException(String message) {
		super(new ErrorResponse(message, HttpStatus.FORBIDDEN));
	}
}
