package com.example.finance.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String uf;
	
	private String street;
	
	private String city;
	
	private String cep;
	
	private String country;
	
	private int number;
	
	@Override
	public String toString() {
		return this.city;
	}
}