package com.fiap.intelligrid.controller;

import com.fiap.intelligrid.domain.exception.DefaultException;
import com.fiap.intelligrid.domain.request.EnderecoRequest;
import com.fiap.intelligrid.domain.response.EnderecoResponse;
import com.fiap.intelligrid.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/endereco")
@RestController
public class EnderecoController {

	private final EnderecoService enderecoService;

	private EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	@GetMapping("/cep/{cep}")
	public ResponseEntity<?> fetchByCep(@PathVariable String cep) {
		try {
			return ResponseEntity.ok(
					enderecoService.buscaPorCep(cep)
			);
		} catch (DefaultException e) {
			return ResponseEntity.status(
					e.getErrorResponse().status()
			).body(e.getErrorResponse());
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody EnderecoRequest enderecoRequest) {
		try {
			enderecoService.create(enderecoRequest);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (DefaultException e) {
			return ResponseEntity.status(
					e.getErrorResponse().status()
			).body(e.getErrorResponse());
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody EnderecoRequest enderecoRequest) {
		try {
			enderecoService.update(enderecoRequest);
			return ResponseEntity.ok().build();
		} catch (DefaultException e) {
			return ResponseEntity.status(
					e.getErrorResponse().status()
			).body(e.getErrorResponse());
		}
	}

	@DeleteMapping
	public ResponseEntity<?> delete(Long id) {
		try {
			enderecoService.delete(id);
			return ResponseEntity.ok().build();
		} catch (DefaultException e) {
			return ResponseEntity.status(
					e.getErrorResponse().status()
			).body(e.getErrorResponse());
		}
	}

}
