package com.fiap.intelligrid.controller;

import com.fiap.intelligrid.domain.exception.EnderecoBadRequestException;
import com.fiap.intelligrid.domain.exception.EnderecoNotFoundException;
import com.fiap.intelligrid.domain.request.EnderecoRequest;
import com.fiap.intelligrid.domain.response.EnderecoResponse;
import com.fiap.intelligrid.service.EnderecoService;
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

	//TODO: faz mais sentido estar em pessoa? Dando GET pessoa/{id}/endereco?
	@GetMapping("/pessoa/{pessoaId}")
	public ResponseEntity<List<EnderecoResponse>> fetchByPerson(@PathVariable Long pessoaId) throws EnderecoNotFoundException {
		//TODO: pegar pessoa da sessao e tirar necessidade de passar id
		return ResponseEntity.ok(enderecoService.buscarPorPessoa(pessoaId));
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody EnderecoRequest enderecoRequest) throws EnderecoBadRequestException {
		enderecoService.create(enderecoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping
	public ResponseEntity<Void> update(@RequestBody EnderecoRequest enderecoRequest) throws EnderecoBadRequestException {
		enderecoService.update(enderecoRequest);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(Long id) throws EnderecoBadRequestException {
		enderecoService.delete(id);
		return ResponseEntity.ok().build();
	}

}

