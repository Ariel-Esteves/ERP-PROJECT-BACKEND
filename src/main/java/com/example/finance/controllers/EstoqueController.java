package com.example.finance.controllers;

import com.example.finance.models.entities.EstoqueEntity;
import com.example.finance.models.entities.ProdutoEntity;
import com.example.finance.models.entities.dto.EstoqueMovimentoDto;
import com.example.finance.services.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("estoque")
public class EstoqueController {

	private final EstoqueService estoqueService;

	public EstoqueController(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}

	@PostMapping
	public ResponseEntity<ProdutoEntity> createEstoqueMovimento(@RequestBody EstoqueMovimentoDto estoqueMovimentoDto) {
		Optional<EstoqueEntity> createdEstoque = estoqueService.createEstoqueMovimento(estoqueMovimentoDto);
		if(createdEstoque.isPresent()){
			return ResponseEntity.ok(createdEstoque.get().getProduto());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<?> getAllEstoque() {
		return ResponseEntity.ok(estoqueService.getAllEstoque());
	}
}
