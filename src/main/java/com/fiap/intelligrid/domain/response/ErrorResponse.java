package com.fiap.intelligrid.domain.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, HttpStatus status) {

}
