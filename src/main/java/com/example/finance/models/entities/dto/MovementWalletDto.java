package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.SaleEntity;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementWalletDto(
		long id,
		@NotNull
		BigDecimal paymentValue,
		LocalDateTime dateTime,
		SaleEntity sale
) {}