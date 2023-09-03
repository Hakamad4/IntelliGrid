package com.fiap.intelligrid.service;

import com.fiap.intelligrid.controller.request.EnderecoRequest;
import com.fiap.intelligrid.controller.response.EnderecoResponse;
import com.fiap.intelligrid.controller.response.EnderecoResponseComEletrodomestico;
import com.fiap.intelligrid.domain.entity.Endereco;
import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.repository.EletrodomesticoRepository;
import com.fiap.intelligrid.domain.repository.EnderecoRepository;
import com.fiap.intelligrid.exceptions.DefaultException;
import com.fiap.intelligrid.exceptions.EletrodomesticoNotFoundException;
import com.fiap.intelligrid.exceptions.EnderecoBadRequestException;
import com.fiap.intelligrid.exceptions.EnderecoNotFoundException;
import com.fiap.intelligrid.exceptions.PessoaNotFoundException;
import com.fiap.intelligrid.integration.viacep.ViaCepService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

	private final ViaCepService viaCepService;
	private final PessoaService pessoaService;
	private final EnderecoRepository enderecoRepository;
	private final EletrodomesticoRepository eletrodomesticoRepository;

	public EnderecoService(ViaCepService viaCepService,
						   PessoaService pessoaService,
						   EnderecoRepository enderecoRepository,
						   EletrodomesticoRepository eletrodomesticoRepository) {
		this.viaCepService = viaCepService;
		this.pessoaService = pessoaService;
		this.enderecoRepository = enderecoRepository;
		this.eletrodomesticoRepository = eletrodomesticoRepository;
	}

	public EnderecoResponse buscaPorCep(String cep) throws EnderecoNotFoundException {
		try {
			return viaCepService.buscaEnderecoPorCep(cep)
					.toEnderecoResponse();
		} catch (Exception e) {

			throw new EnderecoNotFoundException(e.getMessage());
		}
	}

	public List<EnderecoResponseComEletrodomestico> buscarPorPessoa(Long pessoaId) throws PessoaNotFoundException {
		final Pessoa pessoa = pessoaService.buscarPorId(pessoaId);
		return pessoa.getEnderecos().stream().map(EnderecoResponseComEletrodomestico::new).toList();
	}

	@Transactional
	public void create(EnderecoRequest enderecoRequest) throws EnderecoBadRequestException {
		// TODO: associar Endereço ao Admin
		
		try {
			enderecoRepository.save(enderecoRequest.toEntity());
		} catch (Exception e) {
			throw new EnderecoBadRequestException(e.getMessage());
		}
	}

	public EnderecoResponseComEletrodomestico buscarPorId(Long id) throws EnderecoNotFoundException {
		return enderecoRepository.findById(id)
				.map(EnderecoResponseComEletrodomestico::new)
				.orElseThrow(EnderecoNotFoundException::new);
	}

	@Transactional
	public void update(Long id, EnderecoRequest enderecoRequest) throws EnderecoNotFoundException {
		if (!enderecoRepository.existsById(id)) {
			throw new EnderecoNotFoundException("Endereço não encontrado");
		}

		final Endereco endereco = enderecoRequest.toEntity();
		endereco.setId(id);
		enderecoRepository.save(endereco);
	}

	@Transactional
	public void delete(Long id) throws EnderecoNotFoundException {
		final long deleted = enderecoRepository.deleteEnderecoById(id);
		if (deleted == 0) {
			throw new EnderecoNotFoundException("Endereço não encontrado");
		}
	}

	public List<EnderecoResponse> buscaFiltrada(String bairro, String cidade, String logradouro, String cep) {
		return enderecoRepository.findByRuaBairroCidadeCep(logradouro, bairro, cidade, cep).stream()
				.map(EnderecoResponse::new)
				.toList();
	}

    @Transactional
    public void adicionarEletrodomestico(Long idEndereco, Long idEletrodomestico) throws DefaultException {
        var enderecoOpt = enderecoRepository.findById(idEndereco);
        if (enderecoOpt.isEmpty()) {
            throw new EnderecoNotFoundException();
        }

        var eletrodomesticoOpt = eletrodomesticoRepository.findById(idEletrodomestico);
        if (enderecoOpt.isEmpty()) {
            throw new EletrodomesticoNotFoundException();
        }

        var endereco = enderecoOpt.get();
        var eletrodomestico = eletrodomesticoOpt.get();
		endereco.addEletrodomestico(eletrodomestico);
    }

    @Transactional
    public void removerEletrodomestico(Long idEndereco, Long idEletrodomestico) throws DefaultException {
        var enderecoOpt = enderecoRepository.findById(idEndereco);
        if (enderecoOpt.isEmpty()) {
            throw new EnderecoNotFoundException();
        }

        var endereco = enderecoOpt.get();
        var temEletrodomestico = endereco.getEletrodomesticos().stream().filter(eletro -> eletro.getId() == idEletrodomestico).findAny();
        if (temEletrodomestico.isEmpty()) {
            throw new EletrodomesticoNotFoundException("Eletrodoméstico já removido.");
        }

		//? Na implementação atual, ao remover o eletrodomestico, o registro se tornará órfão
		endereco.removerEletrodomestico(temEletrodomestico.get());
    }
}
