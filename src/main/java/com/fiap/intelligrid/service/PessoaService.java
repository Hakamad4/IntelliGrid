package com.fiap.intelligrid.service;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.repository.PessoaRepository;
import com.fiap.intelligrid.domain.response.PessoaResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<PessoaResponse> findAll() {
        return pessoaRepository.findAll().stream().map(PessoaResponse::new).toList();
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}