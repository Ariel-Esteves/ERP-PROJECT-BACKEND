package com.example.finance.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotBlank
	private String uf;
	@NotBlank
	private String rua;
	@NotBlank
	private String cidade;
	@NotBlank
	private String cep;
	@NotBlank
	private String pais;
	@NotNull
	private int numero;

	@Override
	public String toString() {
		return this.cidade;
	}
}