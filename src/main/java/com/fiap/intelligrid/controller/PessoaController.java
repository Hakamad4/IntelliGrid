package com.fiap.intelligrid.controller;

import com.fiap.intelligrid.controller.request.PessoaAtualizacaoRequest;
import com.fiap.intelligrid.controller.request.PessoaRequest;
import com.fiap.intelligrid.controller.response.PessoaResponse;
import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.exceptions.PessoaNotFoundException;
import com.fiap.intelligrid.exceptions.UsuarioNotFoundException;
import com.fiap.intelligrid.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> buscarPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarTodos());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<PessoaResponse>> buscaFiltrada(@RequestParam(required = false) String nome,
                                                              @RequestParam(required = false) String sexo) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscaFiltrada(nome, sexo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPessoaPorId(@PathVariable Long id) throws PessoaNotFoundException {
        return ResponseEntity.ok(pessoaService.buscarResponsePorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarPessoa(@RequestBody @Valid PessoaRequest pessoaRequest) throws UsuarioNotFoundException {
        pessoaService.salvar(pessoaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable Long id, @RequestBody @Valid PessoaAtualizacaoRequest pessoaRequest)
            throws PessoaNotFoundException {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> excluirPessoa(@PathVariable Long id) throws PessoaNotFoundException {
        pessoaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}