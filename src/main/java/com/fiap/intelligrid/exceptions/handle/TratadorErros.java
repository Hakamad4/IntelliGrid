package com.fiap.intelligrid.exceptions.handle;

import com.fiap.intelligrid.controller.response.CamposErroResponse;
import com.fiap.intelligrid.controller.response.ErrorResponse;
import com.fiap.intelligrid.exceptions.DefaultException;
import com.fiap.intelligrid.exceptions.EnderecoBadRequestException;
import com.fiap.intelligrid.exceptions.EnderecoNotFoundException;
import com.fiap.intelligrid.exceptions.PessoaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorErros {

	@ExceptionHandler(PessoaNotFoundException.class)
	public ResponseEntity<ErrorResponse> tratarErroEntidadeNaoEncontrada(PessoaNotFoundException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ex.getErrorResponse());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity tratarErroGenerico(Exception ex) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erro: " + ex.getLocalizedMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErroValidation(MethodArgumentNotValidException ex) {
		List<FieldError> erros = ex.getFieldErrors();

		return ResponseEntity
				.badRequest()
				.body   (new ErrorResponse(
						"Erro de validação",
						HttpStatus.BAD_REQUEST,
						erros.stream().map(CamposErroResponse::new).toList())
				);
	}

	@ExceptionHandler(EnderecoBadRequestException.class)
	public ResponseEntity<ErrorResponse> tratarErroEnderecoBadRequest(EnderecoBadRequestException ex) {
		return ResponseEntity
				.badRequest()
				.body(ex.getErrorResponse());
	}

	@ExceptionHandler(EnderecoNotFoundException.class)
	public ResponseEntity<ErrorResponse> tratarErroEnderecoNotFound(EnderecoNotFoundException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ex.getErrorResponse());
	}

	@ExceptionHandler(DefaultException.class)
	public ResponseEntity<ErrorResponse> tratarErroDefaultException(DefaultException ex) {
		return ResponseEntity
				.status(ex.getErrorResponse().getStatus())
				.body(ex.getErrorResponse());
	}


}