package com.fiap.intelligrid.controller.response;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.entity.enums.Genero;

import java.time.LocalDate;

public record PessoaResponse(Long id, String nome, String email, LocalDate dataNascimento, Genero genero) {
    public PessoaResponse(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getDataNascimento(), pessoa.getGenero());
    }
}