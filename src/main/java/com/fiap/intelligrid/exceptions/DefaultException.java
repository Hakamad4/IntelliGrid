package com.fiap.intelligrid.exceptions;

import com.fiap.intelligrid.domain.response.ErrorResponse;

public class DefaultException extends Exception {

	private ErrorResponse errorResponse;

	public DefaultException(ErrorResponse errorResponse) {
		super(errorResponse.getMensagem());
		this.errorResponse = errorResponse;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}
