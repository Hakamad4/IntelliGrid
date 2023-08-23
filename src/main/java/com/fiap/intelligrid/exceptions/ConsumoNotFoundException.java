package com.fiap.intelligrid.exceptions;

import org.springframework.http.HttpStatus;

import com.fiap.intelligrid.controller.response.ErrorResponse;

public class ConsumoNotFoundException extends DefaultException{

	public ConsumoNotFoundException() {
        this("Entidade não encontrada");
    }

    public ConsumoNotFoundException(String message) {
        super(new ErrorResponse(message, HttpStatus.NOT_FOUND));
    }
	
	
}
