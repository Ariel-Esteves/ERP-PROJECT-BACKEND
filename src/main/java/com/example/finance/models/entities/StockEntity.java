package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class StockEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JsonBackReference
	
	private ProductEntity product;
	
	private BigDecimal quantity;
	private LocalDateTime data;
	@OneToMany(cascade = CascadeType.ALL)
	private List<MovementStockEntity> movementStockEntity = new ArrayList<>();
}
