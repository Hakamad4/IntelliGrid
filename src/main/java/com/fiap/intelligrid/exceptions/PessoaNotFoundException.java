package com.fiap.intelligrid.exceptions;

import com.fiap.intelligrid.controller.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class PessoaNotFoundException extends DefaultException {

    public PessoaNotFoundException() {
        this("Entidade não encontrada");
    }

    public PessoaNotFoundException(String message) {
        super(new ErrorResponse(message, HttpStatus.NOT_FOUND));
    }
}