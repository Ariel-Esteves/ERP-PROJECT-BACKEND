package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.VendaEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarteiraDto {
	//id from carteira
	private long id;
	private BigDecimal valor;
	private VendaEntity venda;
}
