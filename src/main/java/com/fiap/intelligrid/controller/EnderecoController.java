package com.fiap.intelligrid.controller;

import com.fiap.intelligrid.exceptions.PessoaNotFoundException;
import com.fiap.intelligrid.exceptions.EnderecoBadRequestException;
import com.fiap.intelligrid.exceptions.EnderecoNotFoundException;
import com.fiap.intelligrid.controller.request.EnderecoRequest;
import com.fiap.intelligrid.controller.response.EnderecoResponse;
import com.fiap.intelligrid.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	private final EnderecoService enderecoService;

	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	@GetMapping("/cep/{cep}")
	public ResponseEntity<EnderecoResponse> fetchByCep(@PathVariable String cep) throws EnderecoNotFoundException {
		return ResponseEntity.ok(enderecoService.buscaPorCep(cep));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoResponse> fetchById(@PathVariable Long id) throws EnderecoNotFoundException {
		return ResponseEntity.ok(enderecoService.buscarPorId(id));
	}

	@GetMapping("/pessoa/{pessoaId}")
	public ResponseEntity<List<EnderecoResponse>> fetchByPerson(@PathVariable Long pessoaId) throws PessoaNotFoundException {
		return ResponseEntity.ok(enderecoService.buscarPorPessoa(pessoaId));
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody @Valid EnderecoRequest enderecoRequest) throws EnderecoBadRequestException {
		enderecoService.create(enderecoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid EnderecoRequest enderecoRequest) throws EnderecoNotFoundException {
		enderecoService.update(id, enderecoRequest);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws EnderecoNotFoundException {
		enderecoService.delete(id);
		return ResponseEntity.ok().build();
	}
}