package com.fiap.intelligrid.exceptions.handle;

import com.fiap.intelligrid.domain.exception.DefaultException;
import com.fiap.intelligrid.domain.exception.EnderecoBadRequestException;
import com.fiap.intelligrid.domain.exception.EnderecoNotFoundException;
import com.fiap.intelligrid.domain.response.ErrorResponse;
import com.fiap.intelligrid.exceptions.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorErros {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity tratarErroEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErroGenerico(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroValidation(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(EnderecoBadRequestException.class)
    public ResponseEntity<ErrorResponse> tratarErroEnderecoBadRequest(EnderecoBadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getErrorResponse());
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
                .status(ex.getErrorResponse().status())
                .body(ex.getErrorResponse());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}