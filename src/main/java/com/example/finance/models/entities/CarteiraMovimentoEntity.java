package com.example.finance.models.entities;

import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

public class CarteiraMovimentoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private BigDecimal valor;
	private LocalDateTime data;
	private TIPOMOVIMENTO tipo;
	@ManyToOne
	@JsonBackReference
	private CarteiraEntity carteira;
	@ManyToOne
	private VendaEntity venda;

	@Override
	public String toString() {
		return String.valueOf(this.valor);
	}
}