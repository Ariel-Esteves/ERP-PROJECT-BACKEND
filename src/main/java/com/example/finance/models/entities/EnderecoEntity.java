package com.example.finance.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String uf;
	private String rua;
	private String cidade;
	private String cep;
	private String pais;
	private int numero;

	@Override
	public String toString() {
		return this.cidade;
	}
}