package com.fiap.intelligrid.exceptions;

import org.springframework.http.HttpStatus;

import com.fiap.intelligrid.controller.response.ErrorResponse;

public class UsuarioNotFoundException extends DefaultException{

    public UsuarioNotFoundException() {
        this("Entidade não encontrada");
    }

    public UsuarioNotFoundException(String message) {
        super(new ErrorResponse(message, HttpStatus.NOT_FOUND));
    }

}
