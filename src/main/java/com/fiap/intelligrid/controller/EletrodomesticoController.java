package com.fiap.intelligrid.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fiap.intelligrid.controller.request.EletrodomesticoRequest;
import com.fiap.intelligrid.controller.request.EletrodomesticoAtualizacaoRequest;
import com.fiap.intelligrid.controller.response.EletrodomesticoResponse;
import com.fiap.intelligrid.exceptions.EletrodomesticoBadRequestException;
import com.fiap.intelligrid.exceptions.EletrodomesticoNotFoundException;
import com.fiap.intelligrid.service.EletrodomesticoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eletrodomestico")
public class EletrodomesticoController {

	private final EletrodomesticoService eletrodomesticoService;
	
	
	public EletrodomesticoController(EletrodomesticoService eletrodomesticoService) {
		this.eletrodomesticoService = eletrodomesticoService;
	}
	
	@PostMapping
	public ResponseEntity<EletrodomesticoRequest> cadastrarEletrodomestico(@RequestBody @Valid EletrodomesticoRequest eletrodomesticoRequest) throws EletrodomesticoBadRequestException {
		eletrodomesticoService.salvar(eletrodomesticoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EletrodomesticoResponse> buscarEletroeletronicoPorId(@PathVariable Long id) throws EletrodomesticoNotFoundException {
		return ResponseEntity.ok(eletrodomesticoService.buscarResponsePorId(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EletrodomesticoRequest> excluirEletrodomestico(@PathVariable Long id) throws EletrodomesticoNotFoundException {
		eletrodomesticoService.deletar(id);
	    return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EletrodomesticoResponse> atualizarEletrodomestico(@PathVariable Long id, @RequestBody @Valid EletrodomesticoAtualizacaoRequest eletrodomesticoRequest)
	            throws EletrodomesticoNotFoundException {
	    return ResponseEntity.ok(eletrodomesticoService.atualizaEletrodomestico(id, eletrodomesticoRequest ));
	}
	
	@GetMapping
	public ResponseEntity<List<EletrodomesticoResponse>> buscarEletrodomesticos() {
	    return ResponseEntity.status(HttpStatus.OK).body(eletrodomesticoService.buscarTodos());
	}
}
