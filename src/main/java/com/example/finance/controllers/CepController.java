package com.example.finance.controllers;

import com.example.finance.Repositories.CepRepository;
import com.example.finance.models.entities.EntidadeEntity;
import com.example.finance.models.entities.dto.CepDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cep")
public class CepController {
	private final CepRepository cepRepository;
	@Autowired
	public CepController (CepRepository cepRepository){
		this.cepRepository = cepRepository;
	}
	@GetMapping("/{cepValue}")
	public ResponseEntity<CepDto> getCep(@PathVariable String cepValue) {
		System.out.println(cepValue + " tested");
		CepDto cep = cepRepository.getCep(cepValue);
		return ResponseEntity.ok(cep);
	}
}
