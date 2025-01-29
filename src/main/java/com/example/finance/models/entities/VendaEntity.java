package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private BigDecimal valor;
	private LocalDateTime data;
	private double troco;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private TipoVendaEntity tipoVenda;
	@ManyToOne
	@JsonBackReference
	private EntidadeEntity entidade;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<VendaMovimentoEntity> vendaMovimento;
	@OneToOne
	private CarteiraMovimentoEntity carteiraMovimento;
}