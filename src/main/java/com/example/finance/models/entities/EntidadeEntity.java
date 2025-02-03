package com.example.finance.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

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
	@NotBlank
	private String nome;
	@CPF
	private String cpf;
	@Email
	private String email;
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	private EnderecoEntity endereco;
	@ManyToOne(cascade = CascadeType.ALL)

	private EntidadeTipoEntity entidadeTipo;
	@OneToMany(fetch = FetchType.LAZY)
	@JsonManagedReference("entidade-venda")
	private List<VendaEntity> venda;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	@NotNull
	private CarteiraEntity carteira;

	@Override
	public String toString() {
		return this.nome;
	}


}