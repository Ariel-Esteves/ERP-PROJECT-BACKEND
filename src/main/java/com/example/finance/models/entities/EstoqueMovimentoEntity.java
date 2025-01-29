package com.example.finance.models.entities;
import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import jakarta.persistence.*;
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
	private BigDecimal quantidade;
	private TIPOMOVIMENTO tipo;
	@ManyToOne
	private EstoqueEntity estoque;
}