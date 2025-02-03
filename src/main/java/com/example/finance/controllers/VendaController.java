package com.example.finance.controllers;

import com.example.finance.models.entities.TipoVendaEntity;
import com.example.finance.models.entities.VendaEntity;
import com.example.finance.models.entities.VendaMovimentoEntity;
import com.example.finance.models.entities.dto.VendaDto;
import com.example.finance.services.CarteiraService;
import com.example.finance.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class VendaController {
	private final VendaService vendaService;
	private final CarteiraService carteiraService;

	@Autowired
	public VendaController(VendaService vendaService, CarteiraService carteiraService) {
		this.vendaService = vendaService;
		this.carteiraService = carteiraService;
	}

	@PostMapping("/vendas")
	public ResponseEntity<?> createVenda(@RequestBody VendaDto vendaDto) throws Exception {
		VendaEntity createdVenda = vendaService.createVenda(vendaDto);
		return ResponseEntity.ok(createdVenda);
	}

	@GetMapping("/vendas")
	public ResponseEntity<List<VendaEntity>> getAllVendas() {
		List<VendaEntity> vendas = vendaService.getAllVendas();
		return ResponseEntity.ok(vendas);
	}

	@GetMapping("/vendas/{id}")
	public ResponseEntity<VendaEntity> getVendaById(@PathVariable long id) {

		return ResponseEntity.ok(vendaService.getVendaById(id));
	}

	@PutMapping("/vendas/{id}")
	public ResponseEntity<VendaEntity> updateVenda(@PathVariable long id, @RequestBody VendaEntity vendaDetails) throws Exception {
		VendaEntity updatedVenda = vendaService.updateVenda(id, vendaDetails);
		if(updatedVenda != null) {
			return ResponseEntity.ok(updatedVenda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("vendas/{id}")
	public ResponseEntity<Void> deleteVenda(@PathVariable long id) {
		vendaService.deleteVenda(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/vendamovimento")
	public ResponseEntity<List<VendaMovimentoEntity>> getVendaMovimento() {
		List<VendaMovimentoEntity> vendas = vendaService.getVendaMovimento();
		return ResponseEntity.ok(vendas);
	}

	@GetMapping("/tipovenda")
	public ResponseEntity<List<TipoVendaEntity>> getAllTipoVendas() {
		List<TipoVendaEntity> TipoVenda = vendaService.getAllTipoVendas();
		return ResponseEntity.ok(TipoVenda);
	}

	@PostMapping("/tipovenda")
	public ResponseEntity<TipoVendaEntity> createTipoVenda(@RequestBody TipoVendaEntity TipoVenda) {
		TipoVendaEntity createdTipoVenda = vendaService.createTipoVenda(TipoVenda);
		return ResponseEntity.ok(createdTipoVenda);
	}

}