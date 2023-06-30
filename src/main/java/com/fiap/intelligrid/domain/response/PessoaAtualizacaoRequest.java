package com.fiap.intelligrid.domain.response;

import jakarta.validation.constraints.NotNull;

public record PessoaAtualizacaoRequest(String nome, String email) {
}