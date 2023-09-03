package com.fiap.intelligrid.exceptions;

import org.springframework.http.HttpStatus;

import com.fiap.intelligrid.controller.response.ErrorResponse;

public class PessoaBadRequestException extends DefaultException {

	public PessoaBadRequestException() {
		this("Bad Request - Pessoa");
	}

	public PessoaBadRequestException(String message) {
		super(new ErrorResponse(message, HttpStatus.BAD_REQUEST));
	}
}
