package com.example.finance.services;

import com.example.finance.Repositories.*;
import com.example.finance.models.entities.*;
import com.example.finance.models.entities.dto.EstoqueMovimentoDto;
import com.example.finance.models.entities.dto.VendaDto;
import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

	private final EstoqueMovimentoRepository estoqueMovimentoRepository;

	private final CarteiraMovimentoRepository carteiraMovimentoRepository;
	private final CarteiraRepository carteiraRepository;
	private final UserRepository userRepository;

	@Autowired
	public VendaService(VendaRepository vendaRepository, TipoVendaRepository tipoVendaRepository, EntidadeRepository entidadeRepository,
	                    VendaMovimentoRepository vendaMovimentoRepository, ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository,
	                    CarteiraRepository carteiraRepository, EstoqueMovimentoRepository estoqueMovimentoRepository,
	                    CarteiraMovimentoRepository carteiraMovimentoRepository, UserRepository userRepository)
	{
		this.vendaRepository = vendaRepository;
		this.tipoVendaRepository = tipoVendaRepository;
		this.entidadeRepository = entidadeRepository;
		this.vendaMovimentoRepository = vendaMovimentoRepository;
		this.produtoRepository = produtoRepository;
		this.estoqueRepository = estoqueRepository;
		this.carteiraRepository = carteiraRepository;
		this.estoqueMovimentoRepository = estoqueMovimentoRepository;
		this.carteiraMovimentoRepository = carteiraMovimentoRepository;
		this.userRepository = userRepository;
	}


	public VendaEntity createVenda(VendaDto vendaDto) {
		TipoVendaEntity tipoVenda = TipoVendaEntity.builder().nome("Saida").id(0).build();
		UserEntity user = userRepository.findById(vendaDto.getUser()).orElseThrow(() -> new RuntimeException("User not found"));
		EntidadeEntity entidade = entidadeRepository.findById(vendaDto.getEntidade()).orElseThrow(() -> new RuntimeException("Entidade not found"));

		VendaEntity vendaEntity = VendaEntity.builder()
		                                     .valor(vendaDto.getValor())
		                                     .data(LocalDateTime.now())
		                                     .tipoVenda(tipoVenda)
		                                     .entidade(entidade)
		                                     .id(0)
		                                     .user(user)
		                                     .build();

		VendaEntity vendaSaved = vendaRepository.save(vendaEntity);
		entidade.getVenda().add(vendaSaved);
		EntidadeEntity entidadeSaved = entidadeRepository.save(entidade);

		vendaEntity.setEntidade(entidadeSaved);

		user.getVenda().add(vendaSaved);
		userRepository.save(user);

		List<VendaMovimentoEntity> vendaMovimentoList = vendaDto.getVendaMovimento().stream().map(vendaMovimentoEntity -> {

			ProdutoEntity produtoEntity = produtoRepository.findById(vendaMovimentoEntity.getProduto().getId())
			                                               .orElseThrow(() -> new RuntimeException("Produto not found"));

			produtoEntity.getEstoque()
			             .getEstoqueMovimentoEntity()
			             .add(EstoqueMovimentoEntity.builder()
			                                        .quantidade(vendaMovimentoEntity.getQuantidade())
			                                        .tipo(TIPOMOVIMENTO.SAIDA)
			                                        .estoque(produtoEntity.getEstoque())
					                                .vendaEntity(null)
			                                        .id(0)
			                                        .build());

			CarteiraMovimentoEntity carteiraMovimentoEntidade = CarteiraMovimentoEntity.builder()
			                                                                           .venda(vendaSaved)
			                                                                           .valor(vendaMovimentoEntity.getValor())
			                                                                           .carteira(entidade.getCarteira())
			                                                                           .data(LocalDateTime.now())
			                                                                           .tipo(TIPOMOVIMENTO.ENTRADA)
			                                                                           .id(0)
			                                                                           .build();
			carteiraMovimentoRepository.save(carteiraMovimentoEntidade);

			CarteiraMovimentoEntity carteiraMovimentoUser = CarteiraMovimentoEntity.builder()
			                                                                       .venda(vendaSaved)
			                                                                       .valor(vendaMovimentoEntity.getValor())
			                                                                       .tipo(TIPOMOVIMENTO.SAIDA)
			                                                                       .carteira(user.getCarteira())
			                                                                       .data(LocalDateTime.now())
			                                                                       .id(0)
			                                                                       .build();
			carteiraMovimentoRepository.save(carteiraMovimentoUser);

			EstoqueMovimentoEntity estoqueMovimento = EstoqueMovimentoEntity.builder()
			                                                                .vendaEntity(vendaSaved)
			                                                                .estoque(produtoEntity.getEstoque())
			                                                                .quantidade(vendaMovimentoEntity.getQuantidade().negate())
			                                                                .tipo(TIPOMOVIMENTO.SAIDA)
			                                                                .id(0)
			                                                                .build();

			estoqueMovimentoRepository.save(estoqueMovimento);

			return VendaMovimentoEntity.builder().produto(produtoEntity).valor(vendaMovimentoEntity.getValor()).quantidade(vendaMovimentoEntity.getQuantidade()).venda(vendaSaved).id(0).build();

		}).collect(Collectors.toList());

		vendaSaved.setVendaMovimento(vendaMovimentoList);


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
		if(optionalVenda.isPresent()) {
			VendaEntity vendaEntity = optionalVenda.get();
			vendaEntity.setValor(vendaDetails.getValor());
			vendaEntity.setData(vendaDetails.getData());
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


}
