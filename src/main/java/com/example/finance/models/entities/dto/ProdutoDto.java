package com.example.finance.models.entities.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoDto(
		@NotEmpty
		String nome,
		@NotNull
		BigDecimal valor,
		@NotNull
		BigDecimal quantidade,
		long estoque
) {}