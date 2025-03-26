package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MapperDto {
	private MapperDto() {
	}
	
	public static ProductEntity convertToProductEntity(ProductDto productDto) {
		return ProductEntity.builder().name(productDto.name()).price(productDto.price()).stock(null).id(0).build();
	}
	
	public static ProductDto convertToProductDto(ProductEntity productEntity) {
		return new ProductDto(productEntity.getId(), productEntity.getName(), productEntity.getPrice(), productEntity.getStock().getQuantity());
	}
	
	public static WalletEntity convertToWalletEntity(PersonDto personDto) {
		return WalletEntity.builder().person(null).dateTime(LocalDateTime.now()).balance(BigDecimal.ZERO).movement(new ArrayList<>()).id(0).build();
	}
	
	public static PersonDto convertToPersonDto(PersonEntity personSaved) {
		String user = personSaved.getUserEntity() != null ? personSaved.getUserEntity().getLogin() : null;
		return new PersonDto(personSaved.getId(), personSaved.getName(), personSaved.getCpf(), personSaved.getEmail(), personSaved.getAddress(),
		                     personSaved.getPersonType().getName(), user, personSaved.getWallet().getId());
	}
	
	public static PersonTypeEntity convertToPersonTypeEntity(PersonDto personDto) {
		return PersonTypeEntity.builder().id(0).name(personDto.personType()).creation(LocalDateTime.now()).build();
	}
	
	public static AddressEntity convertToAddressEntity(PersonDto personDto) {
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
	
	public static SaleDto convertToSaleDto(SaleEntity saleEntity) {
		return new SaleDto(saleEntity.getId(), saleEntity.getPaymentValue(), saleEntity.getSaleType().getId(), saleEntity.getPerson().getId(),
		                   saleEntity.getMovementSales().stream().map(MapperDto::convertToMovementSaleDto).collect(Collectors.toCollection(ArrayList::new)),
		                   saleEntity.getData().toString());
	}
	
	public static MovementSaleDto convertToMovementSaleDto(MovementSaleEntity movementSaleEntity) {
		return new MovementSaleDto(movementSaleEntity.getId(), movementSaleEntity.getProduct().getId(), movementSaleEntity.getPaymentValue(),
		                           movementSaleEntity.getQuantity());
	}
}
