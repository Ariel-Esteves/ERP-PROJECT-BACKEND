package com.example.finance.controllers;

import com.example.finance.models.entities.EntidadeEntity;
import com.example.finance.models.entities.EntidadeTipoEntity;
import com.example.finance.models.entities.dto.EntidadeDto;
import com.example.finance.services.EntidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entidade")
public class EntidadeController {

	private final EntidadeService entidadeService;

	@Autowired
	public EntidadeController(EntidadeService entidadeService) {
		this.entidadeService = entidadeService;
	}

	@PostMapping
	public ResponseEntity<EntidadeEntity> createEntidade(@RequestBody EntidadeDto entidadeDto) {
		EntidadeEntity createdEntidade = entidadeService.createEntidade(entidadeDto);
		return ResponseEntity.ok(createdEntidade);
	}

	@GetMapping
	public ResponseEntity<List<EntidadeEntity>> getAllEntidades() {
		List<EntidadeEntity> entidades = entidadeService.getAllEntidades();
		return ResponseEntity.ok(entidades);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EntidadeEntity> getEntidadeById(@PathVariable long id) {
		Optional<EntidadeEntity> entidade = entidadeService.getEntidadeById(id);
		return entidade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<EntidadeEntity> updateEntidade(@PathVariable long id, @RequestBody EntidadeEntity entidadeDetails) {
		EntidadeEntity updatedEntidade = entidadeService.updateEntidade(id, entidadeDetails);
		if (updatedEntidade != null) {
			return ResponseEntity.ok(updatedEntidade);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEntidade(@PathVariable long id) {
		entidadeService.deleteEntidade(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/entidadetipo")
	public ResponseEntity<List<EntidadeTipoEntity> > getAllEntidadeTipos() {
		List<EntidadeTipoEntity> entidadeTipos = entidadeService.getAllEntidadeTipos();
		return ResponseEntity.ok(entidadeTipos);
	}

	@PostMapping("/entidadetipo")
	public ResponseEntity<EntidadeTipoEntity> createEntidadeTipo(@RequestBody EntidadeTipoEntity entidadeTipoEntity) {
		EntidadeTipoEntity createdEntidadeTipo = entidadeService.createEntidadeTipo(entidadeTipoEntity);
		return ResponseEntity.ok(createdEntidadeTipo);
	}
}