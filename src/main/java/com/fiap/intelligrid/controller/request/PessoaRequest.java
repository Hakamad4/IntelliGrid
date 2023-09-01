package com.fiap.intelligrid.controller.request;

import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.entity.enums.Genero;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;


//to json
public record PessoaRequest(
		@NotBlank
		String nome,
		@NotNull
		@Past
		LocalDate dataNascimento,
		@NotNull
		Genero genero,
		@NotBlank
		@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
		String email,
		String parentesco,
		Boolean ehAdmin) {

	public Pessoa toEntity() {
		return new Pessoa(
				null,
				nome,
				email,
				dataNascimento,
				genero,
				parentesco,
				ehAdmin,
				null
		);
	}
}