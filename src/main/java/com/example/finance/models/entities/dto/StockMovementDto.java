package com.example.finance.models.entities.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record StockMovementDto(long id, @NotNull BigDecimal quantity

) { }