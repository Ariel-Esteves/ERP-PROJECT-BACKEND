package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.EnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntidadeDto {
	private String nome;
	private String cpf;
	private String email;
	private EnderecoEntity endereco;
	private String tipo;
	private long user;
}
