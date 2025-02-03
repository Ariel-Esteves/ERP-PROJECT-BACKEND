package com.example.finance.models.entities;

import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CarteiraMovimentoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private BigDecimal valor;
	private LocalDateTime data;
	@NotNull
	private TIPOMOVIMENTO tipo;
	@ManyToOne
	@JsonBackReference
	private CarteiraEntity carteira;
	@ManyToOne

	private VendaEntity venda;

}