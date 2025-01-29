package com.example.finance.services;

import com.example.finance.Repositories.*;
import com.example.finance.models.entities.*;
import com.example.finance.models.entities.dto.CarteiraDto;
import com.example.finance.models.entities.dto.EstoqueDto;
import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

	private final VendaRepository vendaRepository;
	private final TipoVendaRepository tipoVendaRepository;
	private final EntidadeRepository entidadeRepository;
	private final VendaMovimentoRepository vendaMovimentoRepository;
	private final ProdutoRepository produtoRepository;
	private final EstoqueRepository estoqueRepository;
	private final CarteiraRepository carteiraRepository;

	public VendaService(VendaRepository vendaRepository, TipoVendaRepository tipoVendaRepository, EntidadeRepository entidadeRepository, VendaMovimentoRepository vendaMovimentoRepository, ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository,
	                    CarteiraRepository carteiraRepository) {
		this.vendaRepository = vendaRepository;
		this.tipoVendaRepository = tipoVendaRepository;
		this.entidadeRepository = entidadeRepository;
		this.vendaMovimentoRepository = vendaMovimentoRepository;
		this.produtoRepository = produtoRepository;
		this.estoqueRepository = estoqueRepository;
		this.carteiraRepository = carteiraRepository;
	}


	public VendaEntity createVenda(VendaEntity vendaEntity) {
		vendaEntity.getVendaMovimento().forEach(vendaMovimentoEntity -> {
			EstoqueMovimentoEntity estoqueMovimentoEntity = EstoqueMovimentoEntity.builder()
			                                                                      .quantidade(vendaMovimentoEntity.getQuantidade())
			                                                                      .tipo(TIPOMOVIMENTO.SAIDA)
			                                                                      .id(0)
			                                                                      .build();
			List<EstoqueMovimentoEntity> estoqueMovimentoEntities = vendaMovimentoEntity.getProduto().getEstoque().getEstoqueMovimentoEntity();
			estoqueMovimentoEntities.add(estoqueMovimentoEntity);

			vendaMovimentoEntity.getProduto().getEstoque().setEstoqueMovimentoEntity(estoqueMovimentoEntities);
			vendaMovimentoEntity.getProduto().getEstoque().setQuantidade(vendaMovimentoEntity.getProduto().getEstoque().getQuantidade().subtract(vendaMovimentoEntity.getQuantidade()));
			// Save EstoqueMovimentoEntity to the repository
			estoqueRepository.save(vendaMovimentoEntity.getProduto().getEstoque());
		});


		vendaEntity.setData(LocalDateTime.now());

		VendaEntity vendaSaved = vendaRepository.save(vendaEntity);

		return vendaRepository.save(vendaSaved);
	}

	public TipoVendaEntity createTipoVenda(TipoVendaEntity tipoVendaEntity) {
		return tipoVendaRepository.save(tipoVendaEntity);
	}
	public List<TipoVendaEntity> getAllTipoVendas() {
		return tipoVendaRepository.findAll();
	}
	public Optional<TipoVendaEntity> getTipoVendaById(long id) {
		return tipoVendaRepository.findById(id);
	}
	public EntidadeEntity createEntidade(EntidadeEntity entidadeEntity) {
		return entidadeRepository.save(entidadeEntity);
	}
	public List<VendaEntity> getAllVendas() {
		return vendaRepository.findAll();
	}

	public Optional<VendaEntity> getVendaById(long id) {
		return vendaRepository.findById(id);
	}

	public VendaEntity updateVenda(long id, VendaEntity vendaDetails) {
		Optional<VendaEntity> optionalVenda = vendaRepository.findById(id);
		if (optionalVenda.isPresent()) {
			VendaEntity vendaEntity = optionalVenda.get();
			vendaEntity.setValor(vendaDetails.getValor());
			vendaEntity.setData(vendaDetails.getData());
			vendaEntity.setTroco(vendaDetails.getTroco());
			vendaEntity.setTipoVenda(vendaDetails.getTipoVenda());
			vendaEntity.setEntidade(vendaDetails.getEntidade());
			return vendaRepository.save(vendaEntity);
		} else {
			return null; // or throw an exception
		}
	}

	public void deleteVenda(long id) {
		vendaRepository.deleteById(id);
	}

	public List<VendaMovimentoEntity> getVendaMovimento() {
		return vendaMovimentoRepository.findAll();
	}

	public ProdutoEntity createProduto(ProdutoEntity produtoEntity) {
		EstoqueEntity estoque = EstoqueEntity.builder().quantidade(BigDecimal.ZERO).data(LocalDateTime.now()).quantidade(produtoEntity.getQuantidade()).id(0).build();
		produtoEntity.setEstoque(estoque);
		return produtoRepository.save(produtoEntity);
	}

	public List<ProdutoEntity> getAllProdutos() {
		return produtoRepository.findAll();
	}

	public void deleteProduto(long id) {
		produtoRepository.deleteById(id);
	}

	public Optional<EstoqueEntity> setStock(EstoqueDto estoqueDto) {
		Optional<EstoqueEntity> estoque = estoqueRepository.findById(estoqueDto.getId());
		if (estoque.isPresent()) {
			EstoqueEntity estoqueEntity = estoque.get();
			EstoqueMovimentoEntity estoqueMovimentoEntity = EstoqueMovimentoEntity.builder()
			                                                                      .quantidade(estoqueDto.getQuantidade())
			                                                                      .id(0)
					.tipo(estoqueDto.getQuantidade().compareTo(BigDecimal.ZERO) < 0 ? TIPOMOVIMENTO.SAIDA : TIPOMOVIMENTO.ENTRADA)
			                                                                      .build();
			List<EstoqueMovimentoEntity> estoqueMovimentoEntities = estoqueEntity.getEstoqueMovimentoEntity();
			estoqueMovimentoEntities.add(estoqueMovimentoEntity);
			estoqueEntity.setEstoqueMovimentoEntity(estoqueMovimentoEntities);
			estoqueEntity.setQuantidade(estoqueEntity.getQuantidade().add(estoqueDto.getQuantidade()));
			estoqueRepository.save(estoqueEntity);
		}
		return estoque;
	}


	}
