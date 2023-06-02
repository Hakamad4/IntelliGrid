package com.fiap.intelligrid.controller;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.request.PessoaRequest;
import com.fiap.intelligrid.domain.response.PessoaAtualizacaoRequest;
import com.fiap.intelligrid.domain.response.PessoaResponse;
import com.fiap.intelligrid.service.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity buscarPessoaPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if(pessoaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new PessoaResponse(pessoaOptional.get()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPessoa(@RequestBody @Valid PessoaRequest pessoaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(new Pessoa(pessoaRequest)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PessoaAtualizacaoRequest pessoaRequest){
        Pessoa pessoa = pessoaService.getById(pessoaRequest.id());
        pessoa.atualizacaoPessoa(pessoaRequest);

        return ResponseEntity.ok(new PessoaResponse(pessoa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if(pessoaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        pessoaService.delete(pessoaOptional.get());
        return ResponseEntity.ok().build();
    }
}