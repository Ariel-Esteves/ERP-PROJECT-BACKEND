package com.example.finance.controllers;

import com.example.finance.models.entities.MovementSaleEntity;
import com.example.finance.models.entities.SaleEntity;
import com.example.finance.models.entities.SaleTypeEntity;
import com.example.finance.models.entities.dto.SaleDto;
import com.example.finance.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sale")
public class SaleController {
	private final SaleService saleService;
	
	@Autowired
	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}
	
	@PostMapping()
	public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDto) throws Exception {
		SaleDto createdSale = saleService.createSale(saleDto);
		return ResponseEntity.ok(createdSale);
	}
	
	@GetMapping()
	public ResponseEntity<List<SaleDto>> getAllSales() {
		return ResponseEntity.ok(saleService.getAllSales());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SaleEntity> getSaleById(@PathVariable long id) {
		
		return ResponseEntity.ok(saleService.getSaleById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SaleEntity> updateSale(@PathVariable long id, @RequestBody SaleEntity vendaDetails) throws Exception {
		SaleEntity updatedSale = saleService.updateSale(id, vendaDetails);
		if(updatedSale != null) {
			return ResponseEntity.ok(updatedSale);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSale(@PathVariable long id) {
		saleService.deleteSale(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/movement")
	public ResponseEntity<List<MovementSaleEntity>> getMovementSale() {
		List<MovementSaleEntity> sales = saleService.getMovementSales();
		return ResponseEntity.ok(sales);
	}
	
	@GetMapping("/saletype")
	public ResponseEntity<List<SaleTypeEntity>> getAllSaleType() {
		List<SaleTypeEntity> SaleType = saleService.getAllSaleTypes();
		return ResponseEntity.ok(SaleType);
	}
	
	@PostMapping("/saletype")
	public ResponseEntity<SaleTypeEntity> createSaleType(@RequestBody SaleTypeEntity SaleType) {
		SaleTypeEntity createdSaleType = saleService.createSaleType(SaleType);
		return ResponseEntity.ok(createdSaleType);
	}
	
}