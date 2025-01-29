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
public class EntidadeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cpf;
	private String email;
	@ManyToOne(cascade = CascadeType.ALL)

	private EnderecoEntity endereco;
	@ManyToOne(cascade = CascadeType.ALL)

	private EntidadeTipoEntity entidadeTipo;
	@OneToMany(fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<VendaEntity> venda;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private CarteiraEntity carteira;

	@Override
	public String toString() {
		return this.nome;
	}


}