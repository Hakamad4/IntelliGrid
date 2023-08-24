package com.fiap.intelligrid.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fiap.intelligrid.controller.request.ConsumoAtualizacaoRequest;
import com.fiap.intelligrid.controller.request.ConsumoRequest;
import com.fiap.intelligrid.controller.response.ConsumoResponse;
import com.fiap.intelligrid.exceptions.ConsumoBadRequestException;
import com.fiap.intelligrid.exceptions.ConsumoNotFoundException;
import com.fiap.intelligrid.exceptions.EletrodomesticoNotFoundException;
import com.fiap.intelligrid.service.ConsumoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consumo")
public class ConsumoController {

	private final ConsumoService consumoService;

	public ConsumoController(ConsumoService consumoService) {
		this.consumoService = consumoService;
	}

	@PostMapping
	public ResponseEntity<ConsumoRequest> cadastrarConsumo(@RequestBody @Valid ConsumoRequest consumoRequest)
			throws ConsumoBadRequestException, EletrodomesticoNotFoundException {
		consumoService.salvar(consumoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ConsumoResponse> buscarConsumoPorId(@PathVariable Long id) throws ConsumoNotFoundException {
		return ResponseEntity.ok(consumoService.buscarResponsePorId(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ConsumoRequest> excluirConsumo(@PathVariable Long id) throws ConsumoNotFoundException {
		consumoService.deletar(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ConsumoResponse> atualizarConsumo(@PathVariable Long id,
			@RequestBody @Valid ConsumoAtualizacaoRequest consumoRequest) throws ConsumoNotFoundException {
		return ResponseEntity.ok(consumoService.atualizaConsumo(id, consumoRequest));
	}

	@GetMapping
	public ResponseEntity<List<ConsumoResponse>> buscarConsumo() {
		return ResponseEntity.status(HttpStatus.OK).body(consumoService.buscarTodos());
	}

}
