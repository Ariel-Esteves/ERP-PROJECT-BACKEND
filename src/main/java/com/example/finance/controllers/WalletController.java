package com.example.finance.controllers;

import com.example.finance.models.entities.WalletEntity;
import com.example.finance.models.entities.dto.MovementWalletDto;
import com.example.finance.models.entities.dto.WalletDto;
import com.example.finance.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wallet")
public class WalletController {
	private final WalletService walletService;
	
	@Autowired
	WalletController(WalletService walletService) {
		this.walletService = walletService;
	}
	
	@PostMapping()
	public ResponseEntity<WalletEntity> createWallet(@RequestBody WalletDto walletDto) {
		return ResponseEntity.ok(walletService.createWallet(walletDto.person()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WalletEntity> getWallet(@PathVariable long id) {
		return ResponseEntity.ok(walletService.getWallet(id));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteWallet(@PathVariable long id) {
		walletService.deleteWallet(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/movement")
	public ResponseEntity<WalletEntity> createWalletMovimento(@RequestBody MovementWalletDto movementWalletDto) throws Exception {
		return ResponseEntity.ok(walletService.createWalletMovement(movementWalletDto));
	}
	
	@GetMapping("/person/{id}")
	public ResponseEntity<WalletEntity> getWalletByPersonId(@PathVariable long id) {
		return ResponseEntity.ok(walletService.findWalletByPersonId(id));
	}
}
