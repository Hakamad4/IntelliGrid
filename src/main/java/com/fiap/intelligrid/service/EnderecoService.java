package com.fiap.intelligrid.service;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.exception.EnderecoBadRequestException;
import com.fiap.intelligrid.domain.exception.EnderecoNotFoundException;
import com.fiap.intelligrid.domain.repository.EnderecoRepository;
import com.fiap.intelligrid.domain.request.EnderecoRequest;
import com.fiap.intelligrid.domain.response.EnderecoResponse;
import com.fiap.intelligrid.integration.viacep.ViaCepService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

	private final ViaCepService viaCepService;
	private final EnderecoRepository enderecoRepository;
	private final PessoaService pessoaService;

	public EnderecoService(ViaCepService viaCepService,
						   EnderecoRepository enderecoRepository,
						   PessoaService pessoaService) {
		this.viaCepService = viaCepService;
		this.enderecoRepository = enderecoRepository;
		this.pessoaService = pessoaService;
	}

	public EnderecoResponse buscaPorCep(String cep) throws EnderecoNotFoundException {
		try {
			return viaCepService.buscaEnderecoPorCep(cep)
					.toEnderecoResponse();
		} catch (Exception e) {

			throw new EnderecoNotFoundException(e.getMessage());
		}
	}

	public List<EnderecoResponse> buscarPorPessoa(Long pessoaId) {
		final Pessoa pessoa = pessoaService.buscarPorId(pessoaId);
		return pessoa.getEnderecos().stream().map(EnderecoResponse::new).toList();
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
