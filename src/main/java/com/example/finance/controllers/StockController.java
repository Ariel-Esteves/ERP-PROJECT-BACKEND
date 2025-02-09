package com.example.finance.controllers;

import com.example.finance.models.entities.ProductEntity;
import com.example.finance.models.entities.StockEntity;
import com.example.finance.models.entities.dto.StockMovementDto;
import com.example.finance.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stock")
public class StockController {

	private final StockService stockService;

	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@PostMapping
	public ResponseEntity<ProductEntity> createStockMovimento(@RequestBody StockMovementDto stockMovementDto) {
		StockEntity createdStock = stockService.createStockMovimento(stockMovementDto);
			return ResponseEntity.ok(createdStock.getProduct());
	}

	@GetMapping
	public ResponseEntity<?> getAllStock() {
		return ResponseEntity.ok(stockService.getAllStock());
	}
}
