package com.example.finance.models.entities.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record SaleDto(
		long id,
		@NotNull
		BigDecimal paymentValue,
		long saleType,
		@NotNull
		long person,
		ArrayList<MovementSaleDto> movementSale,
		
		String dateValue
) {
}