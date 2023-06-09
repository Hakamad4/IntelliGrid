package com.fiap.intelligrid.domain.response;

import jakarta.validation.constraints.NotNull;

public record PessoaAtualizacaoRequest(@NotNull Long id, String nome, String email) {
}