package com.fiap.intelligrid.service;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.repository.PessoaRepository;
import com.fiap.intelligrid.domain.response.PessoaAtualizacaoRequest;
import com.fiap.intelligrid.domain.response.PessoaResponse;
import com.fiap.intelligrid.utils.exceptions.EntidadeNaoEncontradaException;
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

    public Pessoa atualizarPessoa(PessoaAtualizacaoRequest pessoaRequest) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaRequest.id());

        if (pessoa.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Pessoa não encontrada");
        }
        Pessoa pessoa1 = pessoa.get();
        if (pessoaRequest.nome() != null) {
            pessoa1.setNome(pessoaRequest.nome());
        }
        if (pessoaRequest.email() != null) {
            pessoa1.setEmail(pessoaRequest.email());
        }

        return pessoa1;
    }
}