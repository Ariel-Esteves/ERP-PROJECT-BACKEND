package com.example.finance.models.entities;

import com.example.finance.models.entities.enums.MOVEMENTYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementStockEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private BigDecimal quantity;
	
	private MOVEMENTYPE movementType;
	@ManyToOne
	@JsonIgnore
	private StockEntity stock;
}