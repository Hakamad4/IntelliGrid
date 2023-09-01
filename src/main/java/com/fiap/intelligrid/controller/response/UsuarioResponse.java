package com.fiap.intelligrid.controller.response;

import com.fiap.intelligrid.domain.entity.Usuario;

public record UsuarioResponse(Long id, String login) {
    public UsuarioResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}
