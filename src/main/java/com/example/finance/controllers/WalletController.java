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
	private final WalletService WalletService;
	@Autowired
	WalletController(WalletService walletService){
		this.WalletService = walletService;
	}

	@PostMapping("")
	public ResponseEntity<WalletEntity> createCarteira(@RequestBody WalletDto walletDto) {
		return ResponseEntity.ok(WalletService.createCarteira(walletDto.person()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<WalletEntity> getCarteira(@PathVariable long id) throws ClassNotFoundException {
		return ResponseEntity.ok(WalletService.getCarteira(id).orElse(null));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCarteira(@PathVariable long id) {
		WalletService.deleteCarteira(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/movimento")
	public ResponseEntity<WalletEntity> createCarteiraMovimento(@RequestBody MovementWalletDto movementWalletDto) throws Exception {
		return ResponseEntity.ok(WalletService.createCarteiraMovimento(movementWalletDto));
	}
}
