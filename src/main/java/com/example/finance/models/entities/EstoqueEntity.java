package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
public class EstoqueEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JsonBackReference
	@NotNull
	private ProdutoEntity produto;
	@NotNull
	private BigDecimal quantidade;
	private LocalDateTime data;
	@OneToMany(cascade = CascadeType.ALL)
	private List<EstoqueMovimentoEntity> estoqueMovimentoEntity;
}
