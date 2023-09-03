package com.fiap.intelligrid.controller.request;

import com.fiap.intelligrid.domain.entity.Usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest extends PessoaRequest {
    @NotBlank
    String login;

    public Usuario toEntity() {
        return new Usuario(this);
    }
}