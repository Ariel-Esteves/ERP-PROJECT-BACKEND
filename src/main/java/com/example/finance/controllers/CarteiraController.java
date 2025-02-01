package com.example.finance.controllers;

import com.example.finance.models.entities.CarteiraEntity;
import com.example.finance.models.entities.dto.CarteiraMovimentoDto;
import com.example.finance.services.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CarteiraController {
	private final CarteiraService CarteiraService;
	@Autowired
	CarteiraController(CarteiraService carteiraService){
		this.CarteiraService = carteiraService;
	}

	@PostMapping("/carteira")
	public ResponseEntity<CarteiraEntity> createCarteira(@RequestBody CarteiraMovimentoDto carteiraMovimentoDto) {
		return ResponseEntity.ok(CarteiraService.createCarteira(carteiraMovimentoDto));
	}

	@GetMapping("/carteira/{id}")
	public ResponseEntity<CarteiraEntity> getCarteira(@PathVariable long id) {
		return ResponseEntity.ok(CarteiraService.getCarteira(id).orElse(null));
	}

	@DeleteMapping("/carteira/{id}")
	public ResponseEntity<Void> deleteCarteira(@PathVariable long id) {
		CarteiraService.deleteCarteira(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/carteira/movimento")
	public ResponseEntity<CarteiraEntity> postCarteiraMovimento(@RequestBody CarteiraMovimentoDto carteiraMovimentoDto) {
		return ResponseEntity.ok(CarteiraService.postCarteiraMovimento(carteiraMovimentoDto));
	}
}
