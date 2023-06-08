package com.fiap.intelligrid.domain.entity;

import com.fiap.intelligrid.domain.request.PessoaRequest;
import com.fiap.intelligrid.domain.response.PessoaAtualizacaoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    private Genero genero;

    public Pessoa(PessoaRequest pessoaRequest) {
        this.nome = pessoaRequest.nome();
        this.dataNascimento = pessoaRequest.dataNascimento();
        this.genero = pessoaRequest.genero();
        this.email = pessoaRequest.email();
    }



    public void atualizacaoPessoa(PessoaAtualizacaoRequest pessoaAtualizacaoResponse) {

        if (pessoaAtualizacaoResponse.nome() != null) {
            this.nome = pessoaAtualizacaoResponse.nome();
        }
        if(pessoaAtualizacaoResponse.email() != null) {
            this.email = pessoaAtualizacaoResponse.email();
        }
    }
}