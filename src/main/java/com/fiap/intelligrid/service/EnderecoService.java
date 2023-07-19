package com.fiap.intelligrid.service;

import com.fiap.intelligrid.controller.request.EnderecoRequest;
import com.fiap.intelligrid.controller.response.EnderecoResponse;
import com.fiap.intelligrid.domain.entity.Endereco;
import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.repository.EnderecoRepository;
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

	public List<EnderecoResponse> buscarPorPessoa(Long pessoaId) throws PessoaNotFoundException {
		final Pessoa pessoa = pessoaService.buscarPorId(pessoaId);
		return pessoa.getEnderecos().stream().map(EnderecoResponse::new).toList();
	}

	@Transactional
	public void create(EnderecoRequest enderecoRequest) throws EnderecoBadRequestException {
		try {
			enderecoRepository.save(enderecoRequest.toEntity());
		} catch (Exception e) {
			throw new EnderecoBadRequestException(e.getMessage());
		}
	}

	public EnderecoResponse buscarPorId(Long id) throws EnderecoNotFoundException {
		return enderecoRepository.findById(id)
				.map(EnderecoResponse::new)
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
}
