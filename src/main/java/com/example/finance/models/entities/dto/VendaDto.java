package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.VendaMovimentoEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record VendaDto(
		@NotNull
		BigDecimal valor,
		long tipoVenda,
		@NotNull
		long entidade,
		@NotEmpty
		List<VendaMovimentoEntity> vendaMovimento,
		@NotNull
		long user
) {}