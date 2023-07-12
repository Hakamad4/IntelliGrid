package com.fiap.intelligrid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.intelligrid.controller.request.PessoaRequest;
import com.fiap.intelligrid.domain.entity.Genero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testPostPessoaSuccess() throws Exception {
		PessoaRequest pessoaRequest = new PessoaRequest(
				"Denilson da Massa",
				LocalDate.parse("1990-05-10"),
				Genero.MASCULINO,
				"denil.son@gmail.com"
		);

		mockMvc.perform(post("/pessoa")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(pessoaRequest)))
				.andExpect(status().isCreated());
	}

	@Test
	void testPostPessoaInvalidMail() throws Exception {
		PessoaRequest pessoaRequest = new PessoaRequest(
				"Denilson da Massa",
				LocalDate.parse("1990-05-10"),
				Genero.MASCULINO,
				"denis"
		);

		mockMvc.perform(post("/pessoa")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(pessoaRequest)))
				.andExpect(status().is(400));
	}
}
