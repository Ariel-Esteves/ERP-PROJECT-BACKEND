package com.example.finance.services;

import com.example.finance.Repositories.*;
import com.example.finance.models.entities.*;
import com.example.finance.models.entities.dto.VendaDto;
import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	private final EstoqueMovimentoRepository estoqueMovimentoRepository;

	private final CarteiraMovimentoRepository carteiraMovimentoRepository;
	private final CarteiraRepository carteiraRepository;
	private final UserRepository userRepository;
	private final EstoqueService estoqueService;
	@Autowired
	public VendaService(VendaRepository vendaRepository, TipoVendaRepository tipoVendaRepository, EntidadeRepository entidadeRepository,
	                    VendaMovimentoRepository vendaMovimentoRepository, ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository,
	                    CarteiraRepository carteiraRepository, EstoqueMovimentoRepository estoqueMovimentoRepository,
	                    CarteiraMovimentoRepository carteiraMovimentoRepository, UserRepository userRepository, EstoqueService estoqueService)
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
		this.estoqueService = estoqueService;
	}


	public VendaEntity createVenda(@Valid VendaDto vendaDto) throws Exception {
		TipoVendaEntity tipoVenda = TipoVendaEntity.builder().nome("Saida").id(0).build();
		UserEntity user = userRepository.findById(vendaDto.user()).orElseThrow(() -> new Exception("User not found"));
		EntidadeEntity entidade = entidadeRepository.findById(vendaDto.entidade()).orElseThrow(() -> new Exception("Entidade not found"));

		VendaEntity vendaEntity = VendaEntity.builder()
		                                     .valor(vendaDto.valor())
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

		List<VendaMovimentoEntity> vendaMovimentoList = vendaDto.vendaMovimento().stream().map(vendaMovimentoEntity -> {

			ProdutoEntity produtoEntity = null;
			try {
				produtoEntity = produtoRepository.findById(vendaMovimentoEntity.getProduto().getId())
				                                               .orElseThrow(() -> new Exception("Produto not found"));
			} catch(Exception e) {
				throw new RuntimeException(e);
			}

			produtoEntity.getEstoque()
			             .getEstoqueMovimentoEntity()
			             .add(EstoqueMovimentoEntity.builder()
			                                        .quantidade(vendaMovimentoEntity.getQuantidade())
			                                        .tipo(TIPOMOVIMENTO.SAIDA)
			                                        .estoque(produtoEntity.getEstoque())

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

	public TipoVendaEntity createTipoVenda(@Valid TipoVendaEntity tipoVendaEntity) {
		return tipoVendaRepository.save(tipoVendaEntity);
	}

	public List<TipoVendaEntity> getAllTipoVendas() {
		return tipoVendaRepository.findAll();
	}

	public TipoVendaEntity getTipoVendaById(long id) throws Exception {
		return tipoVendaRepository.findById(id).orElseThrow(()-> new RuntimeException("TipoVenda not found"));
	}

	public EntidadeEntity createEntidade(@Valid EntidadeEntity entidadeEntity) {
		return entidadeRepository.save(entidadeEntity);
	}

	public List<VendaEntity> getAllVendas() {
		return vendaRepository.findAll();
	}

	public VendaEntity getVendaById(long id) {
		return vendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda not found"));
	}

	public VendaEntity updateVenda(long id, VendaEntity vendaDetails) throws Exception {
		Optional<VendaEntity> optionalVenda = vendaRepository.findById(id);
		if(!optionalVenda.isPresent()) {
			throw new Exception("Venda not found");
		}
		VendaEntity vendaEntity = optionalVenda.get();
		vendaEntity.setValor(vendaDetails.getValor());
		vendaEntity.setData(vendaDetails.getData());
		vendaEntity.setTipoVenda(vendaDetails.getTipoVenda());
		vendaEntity.setEntidade(vendaDetails.getEntidade());
		return vendaRepository.save(vendaEntity);
	}

	public void deleteVenda(long id) {
		vendaRepository.deleteById(id);
	}

	public List<VendaMovimentoEntity> getVendaMovimento() {
		return vendaMovimentoRepository.findAll();
	}


}
