package com.example.finance.models.entities.dto;

import java.math.BigDecimal;

public record MovementSaleDto(long id, long product, BigDecimal paymentValue, BigDecimal quantity) { }
