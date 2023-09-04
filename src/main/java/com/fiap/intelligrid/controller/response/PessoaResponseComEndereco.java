package com.fiap.intelligrid.controller.response;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.entity.enums.Genero;

import java.time.LocalDate;
import java.util.List;

public record PessoaResponseComEndereco(Long id, String nome, String email, LocalDate dataNascimento, Genero genero,
        String parentesco, List<EnderecoResponse> enderecos) {
    public PessoaResponseComEndereco(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getDataNascimento(), pessoa.getGenero(),
                pessoa.getParentesco(), pessoa.getEnderecos().stream().map(EnderecoResponse::new).toList());
    }
}