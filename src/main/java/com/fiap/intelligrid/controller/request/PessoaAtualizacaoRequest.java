package com.fiap.intelligrid.controller.request;

import java.time.LocalDate;

import com.fiap.intelligrid.config.Regexes;
import com.fiap.intelligrid.domain.entity.enums.Genero;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaAtualizacaoRequest {
    String nome;
    @Email(regexp = Regexes.EMAIL_VALIDATION) String email;
    @Past LocalDate dataNascimento;
    Genero genero;
    String parentesco;
}