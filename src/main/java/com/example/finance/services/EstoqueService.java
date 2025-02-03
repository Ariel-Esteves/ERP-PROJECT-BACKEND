package com.example.finance.services;

import com.example.finance.Repositories.EstoqueRepository;
import com.example.finance.models.entities.EstoqueEntity;
import com.example.finance.models.entities.EstoqueMovimentoEntity;
import com.example.finance.models.entities.dto.EstoqueMovimentoDto;
import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
	private final EstoqueRepository estoqueRepository;

	@Autowired
	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}

	public Optional<EstoqueEntity> createEstoqueMovimento(@Valid EstoqueMovimentoDto estoqueMovimentoDto) {
		Optional<EstoqueEntity> estoque = estoqueRepository.findById(estoqueMovimentoDto.id());
		if(!estoque.isPresent()) {
			throw new RuntimeException("Estoque not found");
		}
		EstoqueEntity estoqueEntity = estoque.get();
		EstoqueMovimentoEntity estoqueMovimentoEntity = EstoqueMovimentoEntity.builder()
		                                                                      .quantidade(estoqueMovimentoDto.quantidade())
		                                                                      .id(0)
		                                                                      .tipo(estoqueMovimentoDto.quantidade()
		                                                                                               .compareTo(
				                                                                                               BigDecimal.ZERO) < 0 ? TIPOMOVIMENTO.SAIDA : TIPOMOVIMENTO.ENTRADA)
		                                                                      .build();
		List<EstoqueMovimentoEntity> estoqueMovimentoEntities = estoqueEntity.getEstoqueMovimentoEntity();
		estoqueMovimentoEntities.add(estoqueMovimentoEntity);
		estoqueEntity.setEstoqueMovimentoEntity(estoqueMovimentoEntities);
		estoqueEntity.setQuantidade(estoqueEntity.getQuantidade().add(estoqueMovimentoDto.quantidade()));
		estoqueRepository.save(estoqueEntity);
		return estoque;
	}

	public List<EstoqueEntity> getAllEstoque() {
		return estoqueRepository.findAll();
	}
}
