package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class CarteiraEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private BigDecimal valor;
	private LocalDateTime data;
	@OneToOne
	@JsonBackReference
	private EntidadeEntity entidade;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CarteiraMovimentoEntity> carteiraMovimentoEntity;
	@Override
	public String toString() {
		return String.valueOf(this.valor);
	}
}