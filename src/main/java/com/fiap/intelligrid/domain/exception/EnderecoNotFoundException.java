package com.fiap.intelligrid.domain.exception;

import com.fiap.intelligrid.domain.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class EnderecoNotFoundException extends DefaultException {

	public EnderecoNotFoundException() {
		this("Endereco Not Found - Endereco");
	}
	public EnderecoNotFoundException(String message) {
		super(new ErrorResponse(message, HttpStatus.NOT_FOUND));
	}
}
