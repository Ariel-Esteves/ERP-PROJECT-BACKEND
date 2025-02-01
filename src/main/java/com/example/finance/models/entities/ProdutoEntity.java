package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private BigDecimal valor;
	private BigDecimal quantidade;
	@OneToOne
	@JsonManagedReference
	private EstoqueEntity estoque;
	@Override
	public String toString() {
		return this.nome;
	}
}