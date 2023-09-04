package com.fiap.intelligrid.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAtualizacaoRequest extends PessoaAtualizacaoRequest {
    String login;
}
