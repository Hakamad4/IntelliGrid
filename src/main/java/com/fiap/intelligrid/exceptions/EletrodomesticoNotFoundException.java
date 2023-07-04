package com.fiap.intelligrid.exceptions;

import org.springframework.http.HttpStatus;

import com.fiap.intelligrid.controller.response.ErrorResponse;

public class EletrodomesticoNotFoundException extends DefaultException{

	public EletrodomesticoNotFoundException() {
        this("Entidade não encontrada");
    }

    public EletrodomesticoNotFoundException(String message) {
        super(new ErrorResponse(message, HttpStatus.NOT_FOUND));
    }
	
	
}
