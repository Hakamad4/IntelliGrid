package com.fiap.intelligrid.exceptions;

import com.fiap.intelligrid.controller.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class EnderecoBadRequestException extends DefaultException {

	public EnderecoBadRequestException() {
		this("Bad Request - Endereco");
	}

	public EnderecoBadRequestException(String message) {
		super(new ErrorResponse(message, HttpStatus.BAD_REQUEST));
	}
}
