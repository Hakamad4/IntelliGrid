package com.fiap.intelligrid.exceptions;

import org.springframework.http.HttpStatus;

import com.fiap.intelligrid.controller.response.ErrorResponse;

public class EletrodomesticoBadRequestException extends DefaultException {

	public EletrodomesticoBadRequestException() {
		this("Bad Request - Eletrodomestico");
	}

	public EletrodomesticoBadRequestException(String message) {
		super(new ErrorResponse(message, HttpStatus.BAD_REQUEST));
	}
}
