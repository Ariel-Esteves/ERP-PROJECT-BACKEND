package com.example.finance.models.entities.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDto(
		@NotEmpty
		String name,
		@NotNull
		BigDecimal price,
		long stock
) {}