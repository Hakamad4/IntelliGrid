package com.fiap.intelligrid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.intelligrid.controller.request.UsuarioRequest;
import com.fiap.intelligrid.controller.response.UsuarioResponse;
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

    public List<UsuarioResponse> buscarTodos() {
        return usuarioRepository.findAll().stream().map(UsuarioResponse::new).toList();
    }

    public UsuarioResponse buscarPorId(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id).map(UsuarioResponse::new).orElseThrow(UsuarioNotFoundException::new);
    }

    public void salvar(UsuarioRequest usuarioRequest) {
        usuarioRepository.save(new Usuario(usuarioRequest));
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest req) throws UsuarioNotFoundException {
        // TODO not found exception
        Usuario usuario = usuarioRepository.findById(id).get();

        if (req.login() != null) {
            usuario.setLogin(req.login());
        } else {
            throw new UsuarioNotFoundException();
        }

        return new UsuarioResponse(usuario);
    }

    @Transactional
    public void deletar(Long id) throws UsuarioNotFoundException {
        long deleted = usuarioRepository.deleteUsuarioById(id);
        if (deleted == 0) {
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }
    }
}
