package com.example.finance.models.entities.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record SaleDto(
		@NotNull
		BigDecimal paymentValue,
		long saleType,
		@NotNull
		long person,
		@NotNull
		long user,
		@NotEmpty
		List<MovementSaleDto> movementSale
) {
}