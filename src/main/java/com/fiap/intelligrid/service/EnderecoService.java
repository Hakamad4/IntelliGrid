package com.fiap.intelligrid.service;

import com.fiap.intelligrid.domain.exception.EnderecoBadRequestException;
import com.fiap.intelligrid.domain.exception.EnderecoNotFoundException;
import com.fiap.intelligrid.domain.repository.EnderecoRepository;
import com.fiap.intelligrid.domain.request.EnderecoRequest;
import com.fiap.intelligrid.domain.response.EnderecoResponse;
import com.fiap.intelligrid.integration.viacep.ViaCepService;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

	private ViaCepService viaCepService;
	private EnderecoRepository enderecoRepository;

	public EnderecoService(ViaCepService viaCepService,
						   EnderecoRepository enderecoRepository) {
		this.viaCepService = viaCepService;
		this.enderecoRepository = enderecoRepository;
	}

	public EnderecoResponse buscaPorCep(String cep) throws EnderecoNotFoundException {
		try {
			return viaCepService.buscaEnderecoPorCep(cep)
					.toEnderecoResponse();
		} catch (Exception e) {

			throw new EnderecoNotFoundException(e.getMessage());
		}
	}

	public EnderecoResponse buscaPorPessoa(Long id) {

		throw new RuntimeException("Not implemented yet");
	}

	public void create(EnderecoRequest enderecoRequest) throws EnderecoBadRequestException {
		try {
			enderecoRepository.save(enderecoRequest.toEntity());
		} catch (Exception e) {

			throw new EnderecoBadRequestException(e.getMessage());
		}
	}

	public void update(EnderecoRequest enderecoRequest) throws EnderecoBadRequestException {
		try {
			enderecoRepository.save(enderecoRequest.toEntity());
		} catch (Exception e) {

			throw new EnderecoBadRequestException(e.getMessage());
		}
	}

	public void delete(Long id) throws EnderecoBadRequestException {
		try {
			enderecoRepository.deleteById(id);
		} catch (Exception e) {

			throw new EnderecoBadRequestException(e.getMessage());
		}
	}
}
