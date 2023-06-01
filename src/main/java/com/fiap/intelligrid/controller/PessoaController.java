package com.fiap.intelligrid.controller;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.request.PessoaRequest;
import com.fiap.intelligrid.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity buscarPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @PostMapping
    public ResponseEntity cadastrarPessoa(@RequestBody @Valid PessoaRequest pessoaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(new Pessoa(pessoaRequest)));
    }
}