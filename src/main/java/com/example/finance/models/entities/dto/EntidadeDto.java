package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.CarteiraEntity;
import com.example.finance.models.entities.EnderecoEntity;
import com.example.finance.models.entities.EntidadeTipoEntity;
import com.example.finance.models.entities.VendaEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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

}
