package com.fiap.intelligrid.controller.request;

import com.fiap.intelligrid.config.Regexes;
import com.fiap.intelligrid.domain.entity.Pessoa;
import com.fiap.intelligrid.domain.entity.enums.Genero;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//to json
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequest {
	// Parâmetros da Entidade
	@NotBlank
	String nome;
	@NotNull
	@Past
	LocalDate dataNascimento;
	@NotNull
	Genero genero;
	@NotBlank
	@Email(regexp = Regexes.EMAIL_VALIDATION)
	String email;
	@NotBlank
	String parentesco;

	// Outros parâmetros
	Long usuarioId;

	public Pessoa toEntity() {
		return new Pessoa(
				null,
				nome,
				email,
				dataNascimento,
				genero,
				parentesco,
				false,
				null,
				null);
	}
}