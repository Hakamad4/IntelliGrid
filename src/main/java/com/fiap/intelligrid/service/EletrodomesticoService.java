package com.fiap.intelligrid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;
import com.fiap.intelligrid.domain.repository.EletrodomesticoRepository;
import com.fiap.intelligrid.controller.request.EletrodomesticoRequest;
import com.fiap.intelligrid.controller.request.EletrodomesticoAtualizacaoRequest;
import com.fiap.intelligrid.controller.response.EletrodomesticoResponse;
import com.fiap.intelligrid.exceptions.EletrodomesticoNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class EletrodomesticoService {

    private final EletrodomesticoRepository eletrodomesticoRepository;


    public EletrodomesticoService(EletrodomesticoRepository eletrodomesticoRepository) {
        this.eletrodomesticoRepository = eletrodomesticoRepository;
    }

    @Transactional
    public void salvar(EletrodomesticoRequest eletrodomesticoRequest) {
        eletrodomesticoRepository.save(new Eletrodomestico(eletrodomesticoRequest));
    }

    public Eletrodomestico buscarPorId(Long id) throws EletrodomesticoNotFoundException {
        Optional<Eletrodomestico> eletrodomestico = eletrodomesticoRepository.findById(id);
        if (eletrodomestico.isEmpty()) {
            throw new EletrodomesticoNotFoundException("Eletrodomestico não encontrado");
        }
        return eletrodomestico.get();
    }

    public EletrodomesticoResponse buscarResponsePorId(Long id) throws EletrodomesticoNotFoundException {
        return new EletrodomesticoResponse(
                buscarPorId(id)
        );
    }

    public List<EletrodomesticoResponse> buscarTodos() {
        return eletrodomesticoRepository.findAll().stream().map(EletrodomesticoResponse::new).toList();
    }

    @Transactional
    public void deletar(Long id) throws EletrodomesticoNotFoundException {
        long deleted = eletrodomesticoRepository.deleteEletrodomesticoById(id);
        if (deleted == 0) {
            throw new EletrodomesticoNotFoundException("Eletrodomestico não encontrado.");
        }
    }

    @Transactional
    public EletrodomesticoResponse atualizaEletrodomestico(Long id, EletrodomesticoAtualizacaoRequest dadosAtualizacao) throws EletrodomesticoNotFoundException {
        Eletrodomestico eletrodomestico = buscarPorId(id);
        if (dadosAtualizacao.nome() != null) {
            eletrodomestico.setNome(dadosAtualizacao.nome());
        }
        if (dadosAtualizacao.modelo() != null) {
            eletrodomestico.setModelo(dadosAtualizacao.modelo());
        }
        if (dadosAtualizacao.potencia() != 0) {
            eletrodomestico.setPotencia(dadosAtualizacao.potencia());
        }
        return new EletrodomesticoResponse(eletrodomestico);
    }
}
