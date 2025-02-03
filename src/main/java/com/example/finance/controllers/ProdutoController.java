package com.example.finance.controllers;

import com.example.finance.models.entities.ProdutoEntity;
import com.example.finance.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

	private final ProdutoService vendaService;

	public ProdutoController(ProdutoService vendaService) {
		this.vendaService = vendaService;
	}

	@PostMapping
	public ResponseEntity<ProdutoEntity> createProduto(@RequestBody ProdutoEntity produto) {
		ProdutoEntity createdProduto = vendaService.createProduto(produto);
		return ResponseEntity.ok(createdProduto);
	}
	@GetMapping
	public ResponseEntity<List<ProdutoEntity>> getAllProdutos() {
		List<ProdutoEntity> produtos = vendaService.getAllProdutos();
		return ResponseEntity.ok(produtos);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduto(@PathVariable long id) {
		vendaService.deleteProduto(id);
		return ResponseEntity.noContent().build();
	}
}
