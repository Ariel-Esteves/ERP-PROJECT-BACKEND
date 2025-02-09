package com.example.finance.services;

import com.example.finance.Repositories.PersonRepository;
import com.example.finance.Repositories.PersonTypeRepository;
import com.example.finance.models.entities.*;
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
		UserEntity user = userService.findByUserId(personDto.user());
		
		PersonTypeEntity personType = convertToPersonTypeEntity(personDto);
		
		AddressEntity address = convertToAddressEntity(personDto);
		
		WalletEntity wallet = convertToWalletEntity(personDto);
		
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
		
		return convertToPersonDto(personSaved);
	}
	
	public List<PersonDto> getAllPersons() {
	    return personRepository.findAll().stream().map(this::convertToPersonDto).toList();
	}
	
	public PersonDto getPersonById(long id) {
		return convertToPersonDto(personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found")));
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
		return convertToPersonDto(personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found")));
	}
	
	public PersonEntity findByPersonEntityId(long id) {
		return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
	}
	
	
	public PersonTypeEntity convertToPersonTypeEntity(PersonDto personDto) {
		return PersonTypeEntity.builder().id(0).name(personDto.personType()).creation(LocalDateTime.now()).build();
	}
	
	public AddressEntity convertToAddressEntity(PersonDto personDto) {
		return AddressEntity.builder()
		                    .id(0)
		                    .city(personDto.address().getCity())
		                    .uf(personDto.address().getUf())
		                    .street(personDto.address().getStreet())
		                    .number(personDto.address().getNumber())
		                    .cep(personDto.address().getCep())
		                    .country(personDto.address().getCountry())
		                    .build();
	}
	
	public WalletEntity convertToWalletEntity(PersonDto personDto) {
		return WalletEntity.builder().person(null).dateTime(LocalDateTime.now()).balance(BigDecimal.ZERO).movementWalletEntity(new ArrayList<>()).id(0).build();
	}
	
	public PersonDto convertToPersonDto(PersonEntity personSaved) {
		return new PersonDto(personSaved.getName(), personSaved.getCpf(), personSaved.getEmail(), personSaved.getAddress(),
		                     personSaved.getPersonType().getName(), personSaved.getUserEntity().getId());
	}
}