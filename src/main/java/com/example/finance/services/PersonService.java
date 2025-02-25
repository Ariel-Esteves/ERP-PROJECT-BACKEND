package com.example.finance.services;

import com.example.finance.Repositories.PersonRepository;
import com.example.finance.Repositories.PersonTypeRepository;
import com.example.finance.models.entities.*;
import com.example.finance.models.entities.dto.MapperDto;
import com.example.finance.models.entities.dto.PersonDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
	
	private final PersonRepository personRepository;
	private final PersonTypeRepository personTypeRepository;
	private final UserService userService;
	
	public PersonService(PersonRepository personRepository, PersonTypeRepository personTypeRepository, UserService userService) {
		this.personRepository = personRepository;
		this.personTypeRepository = personTypeRepository;
		this.userService = userService;
	}
	
	public PersonDto createPerson(@Valid PersonDto personDto) {
		UserEntity user = personDto.user() != null ? userService.findByUserName(personDto.user()) : null;
		
		PersonTypeEntity personType = MapperDto.convertToPersonTypeEntity(personDto);
		
		AddressEntity address = MapperDto.convertToAddressEntity(personDto);
		
		WalletEntity wallet = MapperDto.convertToWalletEntity(personDto);
		
		PersonEntity personEntity = PersonEntity.builder()
		                                        .name(personDto.name())
		                                        .cpf(personDto.cpf())
		                                        .personType(personType)
		                                        .email(personDto.email())
		                                        .address(address)
		                                        .personType(personType)
		                                        .sales(null)
		                                        .wallet(wallet)
		                                        .userEntity(user)
		                                        .id(0)
		                                        .build();
		
		PersonEntity personSaved = personRepository.save(personEntity);
		
		return MapperDto.convertToPersonDto(personSaved);
	}
	
	public List<PersonDto> getAllPersons() {
	    return personRepository.findAll().stream().map(MapperDto::convertToPersonDto).toList();
	}
	
	public PersonDto getPersonById(long id) {
		return MapperDto.convertToPersonDto(personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found")));
	}
	
	public void deletePerson(long id) {
		personRepository.deleteById(id);
	}
	
	
	public List<PersonTypeEntity> getAllPersonTypes() {
		return personTypeRepository.findAll();
	}
	
	public PersonTypeEntity createPersonType(@Valid PersonTypeEntity personTypeEntity) {
		personTypeEntity.setCreation(LocalDateTime.now());
		return personTypeRepository.save(personTypeEntity);
	}
	
	public PersonDto findByPersonId(long id) {
		return MapperDto.convertToPersonDto(personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found")));
	}
	
	public PersonEntity findByPersonEntityId(long id) {
		return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
	}
	
	
	
	

}