package com.example.finance.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	@OneToMany
	private List<SaleEntity> sale = new ArrayList<>();
	@Override
	public String toString() {
		return this.name;
	}
}