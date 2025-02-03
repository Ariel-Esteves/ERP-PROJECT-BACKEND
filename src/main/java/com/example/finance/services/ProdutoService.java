package com.example.finance.services;

import com.example.finance.Repositories.EstoqueRepository;
import com.example.finance.Repositories.ProdutoRepository;
import com.example.finance.models.entities.EstoqueEntity;
import com.example.finance.models.entities.ProdutoEntity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
	private final ProdutoRepository produtoRepository;
	private final EstoqueRepository estoqueRepository;

	public ProdutoService(ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {
		this.produtoRepository = produtoRepository;
		this.estoqueRepository = estoqueRepository;
	}

	public ProdutoEntity createProduto(@Valid ProdutoEntity produtoEntity) {
		ProdutoEntity produtoSaved = produtoRepository.save(produtoEntity);
		EstoqueEntity estoque = EstoqueEntity.builder()
		                                     .quantidade(BigDecimal.ZERO)
		                                     .data(LocalDateTime.now())
		                                     .quantidade(produtoSaved.getQuantidade())
		                                     .id(0)
		                                     .produto(produtoSaved)
		                                     .estoqueMovimentoEntity(new ArrayList<>())
		                                     .build();

		EstoqueEntity estoqueSaved = estoqueRepository.save(estoque);
		produtoSaved.setEstoque(estoqueSaved);
		return produtoRepository.save(produtoSaved);
	}

	public List<ProdutoEntity> getAllProdutos() {
		return produtoRepository.findAll();
	}

	public void deleteProduto(long id) {
		produtoRepository.deleteById(id);
	}
}
