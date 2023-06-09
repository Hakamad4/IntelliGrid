package com.fiap.intelligrid.domain.exception;

import com.fiap.intelligrid.domain.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class EnderecoBadRequestException extends DefaultException {

	public EnderecoBadRequestException() {
		this("Bad Request - Endereco");
	}
	public EnderecoBadRequestException(String message) {
		super(new ErrorResponse(message, HttpStatus.BAD_REQUEST));
	}
}
