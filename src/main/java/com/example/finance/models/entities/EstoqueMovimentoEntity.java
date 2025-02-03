package com.example.finance.models.entities;
import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstoqueMovimentoEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	private BigDecimal quantidade;
	@NotBlank
	private TIPOMOVIMENTO tipo;
	@ManyToOne
	@JsonIgnore
	@NotNull
	private EstoqueEntity estoque;
}