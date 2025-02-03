package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private BigDecimal quantidade;
	@NotNull
	private BigDecimal valor;
	@ManyToOne
	@JsonIgnore
	@NotNull
	private VendaEntity venda;
	@ManyToOne
	@NotNull
	private ProdutoEntity produto;
	@Override
	public String toString() {
		return String.valueOf(this.quantidade);
	}
}