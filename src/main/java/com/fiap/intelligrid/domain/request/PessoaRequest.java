package com.fiap.intelligrid.domain.request;

import com.fiap.intelligrid.domain.entity.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record PessoaRequest(
        @NotBlank
        String nome,
        @NotNull
        @Past
        LocalDate dataNascimento,
        @NotNull
        Genero genero,

        @NotBlank
        String email
        ) {
}