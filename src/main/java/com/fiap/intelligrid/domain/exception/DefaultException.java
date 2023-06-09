package com.fiap.intelligrid.domain.exception;

import com.fiap.intelligrid.domain.response.ErrorResponse;

public class DefaultException extends Exception {

	private ErrorResponse errorResponse;

	public DefaultException(ErrorResponse errorResponse) {
		super(errorResponse.message());
		this.errorResponse = errorResponse;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}
