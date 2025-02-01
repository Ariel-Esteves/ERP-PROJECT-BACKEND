package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.VendaMovimentoEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaDto {
	private BigDecimal Valor;
	private long tipoVenda;
	private long entidade;
	private List<VendaMovimentoEntity> vendaMovimento;
	private long user;
}
