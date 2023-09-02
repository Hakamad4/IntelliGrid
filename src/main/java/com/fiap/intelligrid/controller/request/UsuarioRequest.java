package com.fiap.intelligrid.controller.request;

import com.fiap.intelligrid.domain.entity.Usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
    @NotBlank
    String login
) {
    public Usuario toEntity() {
        return new Usuario(null, login, null);
    }
}
