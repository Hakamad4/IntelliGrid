package com.fiap.intelligrid.exceptions;

import org.springframework.http.HttpStatus;

import com.fiap.intelligrid.controller.response.ErrorResponse;

public class ConsumoBadRequestException extends DefaultException {

	public ConsumoBadRequestException() {
		this("Bad Request - Consumo");
	}

	public ConsumoBadRequestException(String message) {
		super(new ErrorResponse(message, HttpStatus.BAD_REQUEST));
	}
}
