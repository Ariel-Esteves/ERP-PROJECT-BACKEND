package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class CarteiraEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	private BigDecimal valor;
	private LocalDateTime data;
	@OneToOne
	@JsonBackReference
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private EntidadeEntity entidade;
	@OneToMany(cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<CarteiraMovimentoEntity> carteiraMovimentoEntity;
	@OneToOne
	@JsonBackReference("user-carteira")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UserEntity user;
}