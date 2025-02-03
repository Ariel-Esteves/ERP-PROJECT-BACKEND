package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String nome;
	@NotNull
	private BigDecimal valor;
	@NotNull
	private BigDecimal quantidade;
	@OneToOne
	@JsonManagedReference
	private EstoqueEntity estoque;
	@OneToMany
	private List<VendaMovimentoEntity> vendaMovimento;
	@Override
	public String toString() {
		return this.nome;
	}
}