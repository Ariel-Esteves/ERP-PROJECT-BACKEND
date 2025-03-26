package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	@JsonManagedReference("sale-person")
	private PersonEntity person;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MovementSaleEntity> movementSales = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference("sale-wallet")
	private List<MovementWalletEntity> movementWallet = new ArrayList<>();

}