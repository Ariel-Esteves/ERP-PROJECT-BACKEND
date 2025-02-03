package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.EnderecoEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record EntidadeDto(
		@NotBlank
		String nome,
		@CPF
		String cpf,
		@Email
		String email,
		@NotNull
		EnderecoEntity endereco,
		@NotEmpty
		String tipo,
		long user
) {}