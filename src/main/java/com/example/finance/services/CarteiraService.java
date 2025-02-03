package com.example.finance.services;

import com.example.finance.Repositories.CarteiraRepository;
import com.example.finance.models.entities.CarteiraEntity;
import com.example.finance.models.entities.CarteiraMovimentoEntity;
import com.example.finance.models.entities.dto.CarteiraMovimentoDto;
import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class CarteiraService {
	private final CarteiraRepository carteiraRepository;

	@Autowired
	CarteiraService(CarteiraRepository carteiraRepository) {
		this.carteiraRepository = carteiraRepository;
	}

	public Optional<CarteiraEntity> getCarteira(long id) {
		Optional<CarteiraEntity> carteira = carteiraRepository.findById(id);
		if(!carteira.isPresent()) {
			throw new RuntimeException("Carteira not found");
		}
		return carteira;}

	public CarteiraEntity createCarteira(@Valid CarteiraMovimentoDto carteiraMovimentoDto) {
		CarteiraEntity carteiraEntity = CarteiraEntity.builder()
		                                             .data(LocalDateTime.now())
		                                             .valor(BigDecimal.ZERO)
		                                              .entidade(null)
		                                              .user(carteiraMovimentoDto.user())
		                                             .id(0).build();
		return carteiraRepository.save(carteiraEntity);
	}

	public void deleteCarteira(long id) {
		carteiraRepository.deleteById(id);
	}


	public CarteiraEntity createCarteiraMovimento(@Valid CarteiraMovimentoDto carteiraMovimentoDto) throws Exception {
		CarteiraEntity carteiraEntity = carteiraRepository.findById(carteiraMovimentoDto.id())
		                                                  .orElseThrow(() -> new Exception("Carteira not found"));

		carteiraEntity.setValor(carteiraEntity.getValor().add(carteiraMovimentoDto.valor()));

		CarteiraMovimentoEntity carteiraMovimentoEntity = CarteiraMovimentoEntity.builder()
		                                                                         .carteira(carteiraEntity)
		                                                                         .data(LocalDateTime.now())
		                                                                         .tipo(carteiraMovimentoDto.valor().compareTo(BigDecimal.ZERO) > 0 ? TIPOMOVIMENTO.ENTRADA : TIPOMOVIMENTO.SAIDA)
		                                                                         .valor(carteiraMovimentoDto.valor())
		                                                                         .venda(carteiraMovimentoDto.venda())
		                                                                         .id(0L)
		                                                                         .build();

		carteiraEntity.getCarteiraMovimentoEntity().add(carteiraMovimentoEntity);
		return carteiraRepository.save(carteiraEntity);
	}


}
