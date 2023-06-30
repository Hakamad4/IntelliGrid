package com.fiap.intelligrid.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fiap.intelligrid.domain.request.EletrodomesticoRequest;
import com.fiap.intelligrid.domain.response.EletrodomesticoAtualizacaoRequest;
import com.fiap.intelligrid.domain.response.EletrodomesticoResponse;
import com.fiap.intelligrid.exceptions.EletrodomesticoNotFoundException;
import com.fiap.intelligrid.exceptions.EnderecoBadRequestException;
import com.fiap.intelligrid.service.EletrodomesticoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eletrodomestico")
public class EletrodomesticoController {

	private final EletrodomesticoService eletrodomesticoservice;
	
	
	public EletrodomesticoController(EletrodomesticoService eletrodomesticoservice) {
		this.eletrodomesticoservice = eletrodomesticoservice;
	}
	
	@PostMapping
	public ResponseEntity<EletrodomesticoRequest> cadastrarEletrodomestico(@RequestBody @Valid EletrodomesticoRequest eletrodomesticoRequest) throws EnderecoBadRequestException {
		eletrodomesticoservice.salvar(eletrodomesticoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(eletrodomesticoRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EletrodomesticoResponse> buscarEletroeletronicoPorId(@PathVariable Long id) throws EletrodomesticoNotFoundException {
		return ResponseEntity.ok(eletrodomesticoservice.buscarResponsePorId(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EletrodomesticoRequest> excluirEletrodomestico(@PathVariable Long id) throws EletrodomesticoNotFoundException {
		eletrodomesticoservice.deletar(id);
	    return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EletrodomesticoResponse> atualizarEletrodomestico(@PathVariable Long id, @RequestBody @Valid EletrodomesticoAtualizacaoRequest eletrodomesticoRequest)
	            throws EletrodomesticoNotFoundException {
	    return ResponseEntity.ok(eletrodomesticoservice.atualizaEletrodomestico(id, eletrodomesticoRequest ));
	}
	
	@GetMapping
	public ResponseEntity<List<EletrodomesticoResponse>> buscarEletrodomesticos() {
	    return ResponseEntity.status(HttpStatus.OK).body(eletrodomesticoservice.buscarTodos());
	}
}
