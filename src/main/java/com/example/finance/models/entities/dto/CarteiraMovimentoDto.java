package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.UserEntity;
import com.example.finance.models.entities.VendaEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarteiraMovimentoDto {
	//id from carteira
	private long id;
	private BigDecimal valor;
	private VendaEntity venda;
	private UserEntity user;
}
