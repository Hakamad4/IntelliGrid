package com.fiap.intelligrid.service;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.repository.PessoaRepository;
import com.fiap.intelligrid.domain.response.PessoaAtualizacaoRequest;
import com.fiap.intelligrid.domain.response.PessoaResponse;
import com.fiap.intelligrid.exceptions.EntidadeNaoEncontradaException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<PessoaResponse> buscarTodos() {
        return pessoaRepository.findAll().stream().map(PessoaResponse::new).toList();
    }

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }


    public Pessoa buscarPorId(Long id) throws EntidadeNaoEncontradaException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Pessoa não encontrada");
        }
        return pessoa.get();
    }

    public void deletar(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }

    @Transactional
    public Pessoa atualizarPessoa(PessoaAtualizacaoRequest dadosAtualizacao) {

        Optional<Pessoa> verificacaoPessoa = pessoaRepository.findById(dadosAtualizacao.id());

        if (verificacaoPessoa.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Pessoa não encontrada");
        }
        Pessoa pessoa = verificacaoPessoa.get();
        if (dadosAtualizacao.nome() != null) {
            pessoa.setNome(dadosAtualizacao.nome());
        }
        if (dadosAtualizacao.email() != null) {
            pessoa.setEmail(dadosAtualizacao.email());
        }

        return pessoa;
    }
}