package com.fiap.intelligrid.service;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.repository.PessoaRepository;
import com.fiap.intelligrid.domain.response.PessoaResponse;
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


    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public void deletar(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }
}