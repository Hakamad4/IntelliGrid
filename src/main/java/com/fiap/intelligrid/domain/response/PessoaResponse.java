package com.fiap.intelligrid.domain.response;

import com.fiap.intelligrid.domain.entity.Genero;
import com.fiap.intelligrid.domain.entity.Pessoa;

import java.time.LocalDate;

public record PessoaResponse(String nome, LocalDate dataNascimento, Genero sexo ) {
    public PessoaResponse(Pessoa pessoa){
        this(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getSexo());
    }
}