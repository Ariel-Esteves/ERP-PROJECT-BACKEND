package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private BigDecimal valor;
	private LocalDateTime data;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@NotNull
	private TipoVendaEntity tipoVenda;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonBackReference("entidade-venda")
	private EntidadeEntity entidade;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotEmpty
	private List<VendaMovimentoEntity> vendaMovimento;
	@OneToMany
	@JsonIgnore
	@NotEmpty
	private List<CarteiraMovimentoEntity> carteiraMovimento;
	@ManyToOne
	@JsonBackReference("user-venda")
	private UserEntity user;
}