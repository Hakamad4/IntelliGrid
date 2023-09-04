package com.fiap.intelligrid.controller.response;

import java.util.List;

import com.fiap.intelligrid.domain.entity.Usuario;

public record UsuarioResponse(Long id, String login, List<PessoaResponse> pessoa) {
    public UsuarioResponse(Usuario usuario) {
        this(   usuario.getId(), 
                usuario.getLogin(), 
                usuario.getPessoas().stream().map(PessoaResponse::new).toList());
    }
}
