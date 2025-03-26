package com.example.finance.controllers;

import com.example.finance.models.entities.ProductEntity;
import com.example.finance.models.entities.dto.ProductDto;
import com.example.finance.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

	private final ProductService vendaService;

	public ProductController(ProductService vendaService) {
		this.vendaService = vendaService;
	}

	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
		return ResponseEntity.ok(vendaService.createProduct(product));
	}
	@GetMapping
	public ResponseEntity<List<ProductEntity>> getAllProducts() {
		List<ProductEntity> produtos = vendaService.getAllProducts();
		return ResponseEntity.ok(produtos);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
		vendaService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductEntity> findProduct(@PathVariable long id) {
		return ResponseEntity.ok(vendaService.findProduct(id));
	}
}
