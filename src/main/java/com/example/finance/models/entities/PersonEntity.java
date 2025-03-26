package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	
	private String cpf;
	
	private String email;
	@ManyToOne(cascade=CascadeType.ALL)
	
	private AddressEntity address;
	@ManyToOne(cascade=CascadeType.ALL)
	private PersonTypeEntity personType;
	@OneToMany(fetch=FetchType.LAZY)
	@JsonBackReference("sale-person")
	private List<SaleEntity> sales;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "wallet_id", referencedColumnName = "id")
	@JsonManagedReference
	private WalletEntity wallet;
	@OneToOne
	private UserEntity userEntity;
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
}