package com.example.finance.models.entities.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDto {
	private String nome;
	private BigDecimal valor;
	private BigDecimal quantidade;
	private long estoque;
}
