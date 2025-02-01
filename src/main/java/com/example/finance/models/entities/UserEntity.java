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
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	/*
	private String email;
	private String password;
	private String role;
	private String token;
	private String refreshToken;
	private String status;
	private String cpf;
	private String cnpj;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String birthDate;
	*/

	@OneToOne
	@JsonManagedReference("user-carteira")
	private CarteiraEntity carteira;
	@OneToMany
	@JsonManagedReference("user-venda")
	private List<VendaEntity> venda;
}
