package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementSaleEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private BigDecimal quantity;
	
	private BigDecimal paymentValue;
	@ManyToOne
	@JsonIgnore
	
	private SaleEntity sale;
	@ManyToOne
	
	private ProductEntity product;
	
	@Override
	public String toString() {
		return String.valueOf(this.quantity);
	}
}