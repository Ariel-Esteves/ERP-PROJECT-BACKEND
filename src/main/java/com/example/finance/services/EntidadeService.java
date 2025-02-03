package com.example.finance.services;

import com.example.finance.Repositories.EntidadeRepository;
import com.example.finance.Repositories.EntidadeTipoRepository;
import com.example.finance.models.entities.CarteiraEntity;
import com.example.finance.models.entities.EnderecoEntity;
import com.example.finance.models.entities.EntidadeEntity;
import com.example.finance.models.entities.EntidadeTipoEntity;
import com.example.finance.models.entities.dto.EntidadeDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntidadeService {

	private final EntidadeRepository entidadeRepository;
	private final EntidadeTipoRepository entidadeTipoRepository;
	@Autowired
	public EntidadeService(EntidadeRepository entidadeRepository, EntidadeTipoRepository entidadeTipoRepository) {
		this.entidadeRepository = entidadeRepository;
		this.entidadeTipoRepository = entidadeTipoRepository;
	}

	public EntidadeEntity createEntidade(@Valid EntidadeDto entidadeDto) {
		EntidadeTipoEntity entidadeTipo = EntidadeTipoEntity.builder()
		                                                    .id(0)
		                                                   .nome(entidadeDto.tipo())
		                                                   .criacao(LocalDateTime.now()).build();
		EnderecoEntity endereco = EnderecoEntity.builder()
		                                        .id(0)
		                                        .cidade(entidadeDto.endereco().getCidade())
		                                        .uf(entidadeDto.endereco().getUf())
												.rua(entidadeDto.endereco().getRua())
				.numero(entidadeDto.endereco().getNumero())
				.cep(entidadeDto.endereco().getCep())
				.pais(entidadeDto.endereco().getPais()).build();

		CarteiraEntity carteira = CarteiraEntity.builder()
		                                        .entidade(null)
		                                        .data(LocalDateTime.now())
		                                        .valor(BigDecimal.ZERO)
												.carteiraMovimentoEntity(new ArrayList<>())
		                                        .id(0).build();

		EntidadeEntity entidadeEntity = EntidadeEntity.builder()
		                                              .nome(entidadeDto.nome())
		                                              .cpf(entidadeDto.cpf())
		                                              .entidadeTipo(entidadeTipo)
		                                              .email(entidadeDto.email())
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

	public EntidadeEntity updateEntidade(long id, EntidadeEntity entidadeDetails) throws Exception {
		Optional<EntidadeEntity> optionalEntidade = entidadeRepository.findById(id);
		if (!optionalEntidade.isPresent()) {
			throw new Exception("Entidade not found");
		}

		EntidadeEntity entidadeEntity = optionalEntidade.get();
		entidadeEntity.setNome(entidadeDetails.getNome());
		entidadeEntity.setCpf(entidadeDetails.getCpf());
		entidadeEntity.setEmail(entidadeDetails.getEmail());
		entidadeEntity.setEndereco(entidadeDetails.getEndereco());
		entidadeEntity.setEntidadeTipo(entidadeDetails.getEntidadeTipo());
		entidadeEntity.setVenda(entidadeDetails.getVenda());
		return entidadeRepository.save(entidadeEntity);
	}

	public void deleteEntidade(long id) {
		entidadeRepository.deleteById(id);
	}



	public List<EntidadeTipoEntity> getAllEntidadeTipos() {
		return entidadeTipoRepository.findAll();
	}

	public EntidadeTipoEntity createEntidadeTipo(@Valid EntidadeTipoEntity entidadeTipoEntity) {
		entidadeTipoEntity.setCriacao(LocalDateTime.now());
		return entidadeTipoRepository.save(entidadeTipoEntity);
	}
}