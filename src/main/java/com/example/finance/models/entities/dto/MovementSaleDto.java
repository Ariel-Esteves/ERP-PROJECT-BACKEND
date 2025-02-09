package com.example.finance.models.entities.dto;

import java.math.BigDecimal;

public record MovementSaleDto(long product, BigDecimal paymentValue, BigDecimal quantity) { }
