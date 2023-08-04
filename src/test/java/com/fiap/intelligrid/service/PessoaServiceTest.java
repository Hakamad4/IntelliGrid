package com.fiap.intelligrid.service;

import com.fiap.intelligrid.controller.request.PessoaRequest;
import com.fiap.intelligrid.controller.response.PessoaResponse;
import com.fiap.intelligrid.domain.entity.Genero;
import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.repository.PessoaRepository;
import com.fiap.intelligrid.exceptions.PessoaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PessoaServiceTest {

	@InjectMocks
	private PessoaService pessoaService;

	@Mock
	private PessoaRepository pessoaRepository;
	private PessoaResponse[] pessoaResponsesTest;
	private List<Pessoa> pessoas = new ArrayList<>();

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);

		pessoaResponsesTest = new PessoaResponse[]{
				new PessoaResponse(1L, "Alex", "alex@teste.com", LocalDate.parse("2000-01-01"), Genero.MASCULINO)
		};

		pessoas.add(new Pessoa(1L, "Alex", "alex@teste.com", LocalDate.parse("2000-01-01"), Genero.MASCULINO, null));
	}


	@Test
	void testBuscarTodosDeveRetornar0Registro() {
		when(pessoaRepository.findAll()).thenReturn(new ArrayList<>());

		List<PessoaResponse> response = pessoaService.buscarTodos();
		assertEquals(0, response.size());
		assertArrayEquals(new PessoaResponse[0], response.toArray());
	}

	@Test
	void testBuscarTodosDeveRetornar1Registro() {
		when(pessoaRepository.findAll()).thenReturn(pessoas);

		List<PessoaResponse> response = pessoaService.buscarTodos();

		assertEquals(1, response.size());
		assertArrayEquals(pessoaResponsesTest, response.toArray());
	}

	@Test
	void testSalvar() {
		PessoaRequest pessoaRequest = new PessoaRequest("Alex", LocalDate.parse("2000-01-01"), Genero.MASCULINO, "alex@teste.com");

		pessoaService.salvar(pessoaRequest);

		verify(pessoaRepository).save(any(Pessoa.class));
	}

	//todo: casos negativos para save

	@Test
	void buscarPorIdDeveRetornarUmaPessoa() {
		when(pessoaRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(pessoas.get(0)));

		Pessoa pessoa = null;
		try {
			pessoa = pessoaService.buscarPorId(1L);
		} catch (PessoaNotFoundException e) {
			throw new RuntimeException(e);
		}

		assertEquals(pessoas.get(0), pessoa);
	}

	@Test
	void buscarPorIdNaoDeveRetornar() {
		when(pessoaRepository.findById(99L)).thenReturn(java.util.Optional.empty());

		assertThrows(PessoaNotFoundException.class, () -> pessoaService.buscarPorId(99L));
	}

	//todo: test para buscarResponsePorId

	//todo: test para delete e update

	@Test
	void deletarDeveExcluirPessoa() throws PessoaNotFoundException {
		Long id = 1L;
		when(pessoaRepository.deletePessoaById(id)).thenReturn(1L);
		pessoaService.deletar(id);
		verify(pessoaRepository).deletePessoaById(id);
	}

	@Test
	void deletarDeveLancarPessoaNotFoundExceptionQuandoPessoaNaoEncontrada() {
		Long id = 99L;
		when(pessoaRepository.deletePessoaById(id)).thenReturn(0L);
		assertThrows(PessoaNotFoundException.class, () -> pessoaService.deletar(id));
	}


}
