package com.example.finance.controllers;

import com.example.finance.Repositories.CepRepository;
import com.example.finance.models.entities.dto.CepDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cep")
public class CepController {
	private final CepRepository cepRepository;
	@Autowired
	public CepController (CepRepository cepRepository){
		this.cepRepository = cepRepository;
	}
	@GetMapping
	public ResponseEntity<CepDto> getCep(@PathVariable String cepValue) {
		CepDto cep = cepRepository.getCep(cepValue);
		return ResponseEntity.ok(cep);
	}
}
