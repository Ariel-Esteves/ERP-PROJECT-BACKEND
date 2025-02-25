package com.example.finance.models.entities;

import com.example.finance.models.entities.enums.MOVEMENTYPE;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MovementWalletEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private BigDecimal paymentValue;
	private LocalDateTime data;
	
	private MOVEMENTYPE movementType;
	@ManyToOne
	@JsonBackReference
	private WalletEntity wallet;
	@ManyToOne
	private SaleEntity sale;

}