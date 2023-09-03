package com.fiap.intelligrid.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fiap.intelligrid.controller.request.UsuarioRequest;
import com.fiap.intelligrid.domain.entity.enums.Genero;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario extends Pessoa {

    // TODO Autenticação e autorização

    @Column(unique = true)
    private String login;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pessoa> pessoas = new ArrayList<>();

    public Usuario(Long id, String nome, String email, LocalDate dataNascimento, Genero genero, String parentesco,
            List<Endereco> enderecos, Usuario usuario, String login) {
        super(id, nome, email, dataNascimento, genero, parentesco, true, enderecos, usuario);
        this.login = login;
    }

    public Usuario(UsuarioRequest req) {
        super(null, req.getNome(), req.getEmail(), req.getDataNascimento(), req.getGenero(), req.getParentesco(), true,
                null, null);
        this.login = req.getLogin();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
}
