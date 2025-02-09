package com.example.finance.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private LocalDateTime creation;
	@OneToMany
	private List<PersonEntity> person;
	@Override
	public String toString() {
		return this.name;
	}
}