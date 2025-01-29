package com.example.finance.services;

import com.example.finance.Repositories.CarteiraRepository;
import com.example.finance.Repositories.EntidadeRepository;
import com.example.finance.Repositories.EntidadeTipoRepository;
import com.example.finance.models.entities.*;
import com.example.finance.models.entities.dto.CarteiraDto;
import com.example.finance.models.entities.dto.EntidadeDto;
import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EntidadeService {

	private final EntidadeRepository entidadeRepository;
	private final CarteiraRepository carteiraRepository;
	private final EntidadeTipoRepository entidadeTipoRepository;
	@Autowired
	public EntidadeService(EntidadeRepository entidadeRepository, CarteiraRepository carteiraRepository, EntidadeTipoRepository entidadeTipoRepository) {
		this.entidadeRepository = entidadeRepository;
		this.carteiraRepository = carteiraRepository;
		this.entidadeTipoRepository = entidadeTipoRepository;
	}

	public EntidadeEntity createEntidade(EntidadeDto entidadeDto) {
		EntidadeTipoEntity entidadeTipo = EntidadeTipoEntity.builder()
						                                                   .id(0)
		                                                   .nome(entidadeDto.getTipo())
		                                                   .criacao(LocalDateTime.now()).build();
		EnderecoEntity endereco = EnderecoEntity.builder()
		                                        .id(0)
		                                        .cidade(entidadeDto.getEndereco().getCidade())
		                                        .uf(entidadeDto.getEndereco().getUf())
												.rua(entidadeDto.getEndereco().getRua())
				.numero(entidadeDto.getEndereco().getNumero())
				.cep(entidadeDto.getEndereco().getCep())
				.pais(entidadeDto.getEndereco().getPais()).build();

		CarteiraEntity carteira = CarteiraEntity.builder()
		                                        .entidade(null)
		                                        .data(LocalDateTime.now())
		                                        .valor(BigDecimal.ZERO)
		                                        .id(0).build();

		EntidadeEntity entidadeEntity = EntidadeEntity.builder()
		                                              .nome(entidadeDto.getNome())
		                                              .cpf(entidadeDto.getCpf())
		                                              .entidadeTipo(entidadeTipo)
		                                              .email(entidadeDto.getEmail())
		                                              .endereco(endereco)
		                                              .entidadeTipo(entidadeTipo)
		                                              .venda(null)
				.carteira(carteira)
		                                              .id(0).build();

		return entidadeRepository.save(entidadeEntity);

	}

	public List<EntidadeEntity> getAllEntidades() {
		return entidadeRepository.findAll();
	}

	public Optional<EntidadeEntity> getEntidadeById(long id) {
		return entidadeRepository.findById(id);
	}

	public EntidadeEntity updateEntidade(long id, EntidadeEntity entidadeDetails) {
		Optional<EntidadeEntity> optionalEntidade = entidadeRepository.findById(id);
		if (optionalEntidade.isPresent()) {
			EntidadeEntity entidadeEntity = optionalEntidade.get();
			entidadeEntity.setNome(entidadeDetails.getNome());
			entidadeEntity.setCpf(entidadeDetails.getCpf());
			entidadeEntity.setEmail(entidadeDetails.getEmail());
			entidadeEntity.setEndereco(entidadeDetails.getEndereco());
			entidadeEntity.setEntidadeTipo(entidadeDetails.getEntidadeTipo());
			entidadeEntity.setVenda(entidadeDetails.getVenda());
			return entidadeRepository.save(entidadeEntity);
		} else {
			return null; // or throw an exception
		}
	}

	public void deleteEntidade(long id) {
		entidadeRepository.deleteById(id);
	}



	public List<EntidadeTipoEntity> getAllEntidadeTipos() {
		return entidadeTipoRepository.findAll();
	}

	public EntidadeTipoEntity createEntidadeTipo(EntidadeTipoEntity entidadeTipoEntity) {
		entidadeTipoEntity.setCriacao(LocalDateTime.now());
		return entidadeTipoRepository.save(entidadeTipoEntity);
	}
}