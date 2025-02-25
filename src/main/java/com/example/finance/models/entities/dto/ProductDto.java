package com.example.finance.models.entities.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public record ProductDto(
		long id,
		@NotEmpty
		String name,
		@NotNull
		BigDecimal price,
		
		BigDecimal stock
) {}