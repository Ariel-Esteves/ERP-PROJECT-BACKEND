package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaMovimentoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private BigDecimal quantidade;
	private BigDecimal valor;
	@ManyToOne
	@JsonIgnore
	private VendaEntity venda;
	@ManyToOne
	private ProdutoEntity produto;
	@Override
	public String toString() {
		return String.valueOf(this.quantidade);
	}
}