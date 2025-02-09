package com.example.finance.models.entities;

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
public class SaleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private BigDecimal paymentValue;
	private LocalDateTime data;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private SaleTypeEntity saleType;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonBackReference("sale-person")
	private PersonEntity person;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MovementSaleEntity> movementSale;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MovementWalletEntity> movementWallet;

}