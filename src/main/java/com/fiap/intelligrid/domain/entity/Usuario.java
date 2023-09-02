package com.fiap.intelligrid.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.fiap.intelligrid.controller.request.UsuarioRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    // TODO Autenticação e autorização

    @Column(unique=true)
    private String login;

    @OneToMany(mappedBy = "usuario")
    private List<Pessoa> pessoas = new ArrayList<>();

    public Usuario(UsuarioRequest req) {
        this.login = req.login();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login =  login;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
}
