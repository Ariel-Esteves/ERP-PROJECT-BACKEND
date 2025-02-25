package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.AddressEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record PersonDto(
		long id,
		@NotBlank
		String name,
		@CPF
		String cpf,
		@Email
		String email,
		@NotNull AddressEntity address,
		@NotEmpty
		String personType,
		String user,
		
		long wallet
) {}