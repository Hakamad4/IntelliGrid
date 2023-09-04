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

import com.fiap.intelligrid.controller.request.UsuarioAtualizacaoRequest;
import com.fiap.intelligrid.controller.request.UsuarioRequest;
import com.fiap.intelligrid.controller.response.UsuarioResponse;
import com.fiap.intelligrid.exceptions.PessoaNotFoundException;
import com.fiap.intelligrid.exceptions.UsuarioNotFoundException;
import com.fiap.intelligrid.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) throws UsuarioNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        usuarioService.salvar(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id,
            @RequestBody @Valid UsuarioAtualizacaoRequest req)
            throws UsuarioNotFoundException, PessoaNotFoundException {
        return ResponseEntity.ok(usuarioService.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) throws UsuarioNotFoundException {
        usuarioService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
