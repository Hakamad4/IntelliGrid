package com.fiap.intelligrid.service;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.entity.Usuario;
import com.fiap.intelligrid.domain.entity.enums.Genero;
import com.fiap.intelligrid.domain.repository.PessoaRepository;
import com.fiap.intelligrid.domain.repository.UsuarioRepository;
import com.fiap.intelligrid.controller.request.PessoaRequest;
import com.fiap.intelligrid.controller.request.PessoaAtualizacaoRequest;
import com.fiap.intelligrid.controller.response.PessoaResponse;
import com.fiap.intelligrid.exceptions.PessoaNotFoundException;
import com.fiap.intelligrid.exceptions.UsuarioNotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final UsuarioRepository usuarioRepository;

    public PessoaService(PessoaRepository pessoaRepository, UsuarioRepository usuarioRepository) {
        this.pessoaRepository = pessoaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PessoaResponse> buscarTodos() {
        return pessoaRepository.findAll().stream().map(PessoaResponse::new).toList();
    }

    //TODO grau de parentesco na pessoa
    public List<PessoaResponse> buscaFiltrada(String nome, String sexo) {
        Genero genero = null;

        if (sexo != null && !sexo.isEmpty()) {
            try {
                genero = Genero.valueOf(sexo.toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Gênero inválido");
            }
        }
        return pessoaRepository.findByNomeSexo(nome, genero).stream()
                .map(PessoaResponse::new)
                .toList();
    }


    @Transactional
    public void salvar(PessoaRequest pessoaRequest) throws UsuarioNotFoundException {

        if (pessoaRequest.getUsuarioId() == null) {
            throw new UsuarioNotFoundException();
        }

        var usuarioOpt = usuarioRepository.findById(pessoaRequest.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            throw new UsuarioNotFoundException("Usuário Administrador não encontrado.");
        }

        var usuario = usuarioOpt.get();
        var pessoa = new Pessoa(pessoaRequest);
        pessoa.setUsuario(usuario);

        pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPorId(Long id) throws PessoaNotFoundException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isEmpty()) {
            throw new PessoaNotFoundException("Pessoa não encontrada");
        }
        return pessoa.get();
    }

    public PessoaResponse buscarResponsePorId(Long id) throws PessoaNotFoundException {
        return new PessoaResponse(
                buscarPorId(id)
        );
    }

    @Transactional
    public void deletar(Long id) throws PessoaNotFoundException {
        long deleted = pessoaRepository.deletePessoaById(id);
        if (deleted == 0) {
            throw new PessoaNotFoundException("Pessoa não encontrada");
        }
    }

    @Transactional
    public PessoaResponse atualizarPessoa(Long id, PessoaAtualizacaoRequest dadosAtualizacao) throws PessoaNotFoundException {
        Pessoa pessoa = buscarPorId(id);

        if (dadosAtualizacao.nome() != null) {
            pessoa.setNome(dadosAtualizacao.nome());
        }
        if (dadosAtualizacao.email() != null) {
            pessoa.setEmail(dadosAtualizacao.email());
        }

        return new PessoaResponse(pessoa);
    }
}