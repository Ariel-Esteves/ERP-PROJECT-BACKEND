package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private BigDecimal price;
	
	private BigDecimal cost;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JsonManagedReference
	private StockEntity stock;
	@OneToMany
	private List<MovementSaleEntity> movementSaleEntity;
	
	@Override
	public String toString() {
		return this.name;
	}
}