package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.UserEntity;
import com.example.finance.models.entities.VendaEntity;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarteiraMovimentoDto(
		long id,
		@NotNull
		BigDecimal valor,
		VendaEntity venda,
		UserEntity user
) {}