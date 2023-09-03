package com.fiap.intelligrid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.intelligrid.controller.request.UsuarioRequest;
import com.fiap.intelligrid.controller.response.UsuarioResponse;
import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.entity.Usuario;
import com.fiap.intelligrid.domain.repository.UsuarioRepository;
import com.fiap.intelligrid.exceptions.UsuarioNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarEntity(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
    }

    public List<UsuarioResponse> buscarTodos() {
        return usuarioRepository.findAll().stream().map(UsuarioResponse::new).toList();
    }

    public UsuarioResponse buscarPorId(Long id) throws UsuarioNotFoundException {
        return new UsuarioResponse(buscarEntity(id));
    }

    public void salvar(UsuarioRequest usuarioRequest) {
        var usuario = new Usuario(usuarioRequest);
        usuario.setUsuario(usuario);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest req) throws UsuarioNotFoundException {
        var usuario = buscarEntity(id);
        if (req.getLogin() != null) {
            usuario.setLogin(req.getLogin());
        }
        return new UsuarioResponse(usuario);
    }

    @Transactional
    public void deletar(Long id) throws UsuarioNotFoundException {
        // Necessário remover self-reference
        var usuario = buscarEntity(id);
        ((Pessoa) usuario).setUsuario(null);

        usuarioRepository.deleteUsuarioById(id);
    }
}
