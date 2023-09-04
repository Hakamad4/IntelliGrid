package com.fiap.intelligrid.controller;

import com.fiap.intelligrid.exceptions.PessoaNotFoundException;
import com.fiap.intelligrid.exceptions.DefaultException;
import com.fiap.intelligrid.exceptions.EnderecoBadRequestException;
import com.fiap.intelligrid.exceptions.EnderecoNotFoundException;
import com.fiap.intelligrid.controller.request.EnderecoRequest;
import com.fiap.intelligrid.controller.response.EnderecoResponse;
import com.fiap.intelligrid.controller.response.EnderecoResponseComEletrodomestico;
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
	public ResponseEntity<EnderecoResponseComEletrodomestico> fetchById(@PathVariable Long id) throws EnderecoNotFoundException {
		return ResponseEntity.ok(enderecoService.buscarPorId(id));
	}

	@GetMapping
	public ResponseEntity<List<EnderecoResponse>> buscaFiltrada(@RequestParam(required = false) String bairro,
																@RequestParam(required = false) String cidade,
																@RequestParam(required = false) String logradouro,
																@RequestParam(required = false) String cep) {

		return ResponseEntity.ok(enderecoService.buscaFiltrada(bairro, cidade, logradouro, cep));
	}

	@GetMapping("/pessoa/{pessoaId}")
	public ResponseEntity<List<EnderecoResponseComEletrodomestico>> fetchByPerson(@PathVariable Long pessoaId) throws PessoaNotFoundException {
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

	@PutMapping("/{idEndereco}/eletrodomestico/{idEletrodomestico}")
    public ResponseEntity<Void> incluirEndereco(@PathVariable Long idEndereco, @PathVariable Long idEletrodomestico)
            throws DefaultException {
        enderecoService.adicionarEletrodomestico(idEndereco, idEletrodomestico);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idEndereco}/eletrodomestico/{idEletrodomestico}")
    public ResponseEntity<Void> removeEndereco(@PathVariable Long idEndereco, @PathVariable Long idEletrodomestico)
            throws DefaultException {
        enderecoService.removerEletrodomestico(idEndereco, idEletrodomestico);
        return ResponseEntity.ok().build();
    }
}