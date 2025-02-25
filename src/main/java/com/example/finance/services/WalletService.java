package com.example.finance.services;

import com.example.finance.Repositories.SalesRepository;
import com.example.finance.Repositories.WalletRepository;
import com.example.finance.models.entities.MovementWalletEntity;
import com.example.finance.models.entities.PersonEntity;
import com.example.finance.models.entities.SaleEntity;
import com.example.finance.models.entities.WalletEntity;
import com.example.finance.models.entities.dto.MovementWalletDto;
import com.example.finance.models.entities.enums.MOVEMENTYPE;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WalletService {
	private final WalletRepository walletRepository;
	private final PersonService personService;
	private final SalesRepository salesRepository;
	
	@Autowired
	WalletService(WalletRepository walletRepository, PersonService personService, SalesRepository salesRepository) {
		this.walletRepository = walletRepository;
		this.personService = personService;
		this.salesRepository = salesRepository;
	}

	public WalletEntity getWallet(long id)  {
		return walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
	}

	public WalletEntity createWallet(@Valid long idPerson) {
		PersonEntity person = personService.findByPersonEntityId(idPerson);
		WalletEntity walletEntity = WalletEntity.builder().dateTime(LocalDateTime.now()).balance(BigDecimal.ZERO).person(person).id(0).build();
		return walletRepository.save(walletEntity);
	}

	public void deleteWallet(long id) {
		walletRepository.deleteById(id);
	}


	public WalletEntity createWalletMovement(@Valid MovementWalletDto movementWalletDto) throws Exception {
		WalletEntity walletEntity = walletRepository.findById(movementWalletDto.id()).orElseThrow(() -> new Exception("Wallet not found"));
		SaleEntity saleEntity = salesRepository.findById(movementWalletDto.sale()).orElse(null);
		walletEntity.setBalance(walletEntity.getBalance().add(movementWalletDto.paymentValue()));
		MovementWalletEntity movementWalletEntity = MovementWalletEntity.builder()
		                                                                .wallet(walletEntity)
		                                                                .data(LocalDateTime.now())
		                                                                .movementType(movementWalletDto.paymentValue()
		                                                                                               .compareTo(
				                                                                                               BigDecimal.ZERO) > 0 ? MOVEMENTYPE.ENTRADA : MOVEMENTYPE.SAIDA)
		                                                                .paymentValue(movementWalletDto.paymentValue())
		                                                                .sale(saleEntity)
		                                                                .id(0L)
		                                                                .build();

		walletEntity.getMovementWalletEntity().add(movementWalletEntity);
		return walletRepository.save(walletEntity);
	}
	
	public WalletEntity findWalletByPersonId(long id) {
		return walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
	}
	
}
