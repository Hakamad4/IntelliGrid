package com.fiap.intelligrid.controller;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.request.PessoaRequest;
import com.fiap.intelligrid.domain.response.PessoaAtualizacaoRequest;
import com.fiap.intelligrid.domain.response.PessoaResponse;
import com.fiap.intelligrid.utils.exceptions.EntidadeNaoEncontradaException;
import com.fiap.intelligrid.service.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> buscarPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPessoaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(new PessoaResponse(pessoaService.buscarPorId(id)));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody @Valid PessoaRequest pessoaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.salvar(new Pessoa(pessoaRequest)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PessoaResponse> atualizarPessoa(@RequestBody @Valid PessoaAtualizacaoRequest pessoaRequest) {
        Pessoa pessoa = pessoaService.atualizarPessoa(pessoaRequest);
        return ResponseEntity.ok(new PessoaResponse(pessoa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Pessoa> excluirPessoa(@PathVariable Long id) {
        pessoaService.deletar(pessoaService.buscarPorId(id));
        return ResponseEntity.ok().build();
    }
}