package com.example.finance.controllers;

import com.example.finance.models.entities.PersonTypeEntity;
import com.example.finance.models.entities.dto.PersonDto;
import com.example.finance.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping
	public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
		PersonDto createdPerson = personService.createPerson(personDto);
		return ResponseEntity.ok(createdPerson);
	}

	@GetMapping
	public ResponseEntity<List<PersonDto>> getAllPersons() {
		return ResponseEntity.ok(personService.getAllPersons());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonDto> getPersonById(@PathVariable long id) {
		return ResponseEntity.ok(personService.getPersonById(id));
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable long id) {
		personService.deletePerson(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/entidadetipo")
	public ResponseEntity<List<PersonTypeEntity> > getAllPersonTipos() {
		List<PersonTypeEntity> personTypes = personService.getAllPersonTypes();
		return ResponseEntity.ok(personTypes);
	}

	@PostMapping("/entidadetipo")
	public ResponseEntity<PersonTypeEntity> createdPersonType(@RequestBody PersonTypeEntity personTypeEntity) {
		PersonTypeEntity createdPersonType = personService.createPersonType(personTypeEntity);
		return ResponseEntity.ok(createdPersonType);
	}
}