package com.example.finance.models.entities.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EstoqueMovimentoDto(
		long id,
		@NotNull
		BigDecimal quantidade

) {}