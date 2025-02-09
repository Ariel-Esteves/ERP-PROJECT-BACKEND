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
public class WalletEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	private BigDecimal balance;
	private LocalDateTime dateTime;
	@OneToOne
	@JsonBackReference
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private PersonEntity person;
	@OneToMany(cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<MovementWalletEntity> movementWalletEntity;

}