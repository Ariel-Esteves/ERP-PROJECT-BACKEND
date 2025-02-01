package com.example.finance.services;

import com.example.finance.Repositories.CarteiraRepository;
import com.example.finance.models.entities.CarteiraEntity;
import com.example.finance.models.entities.CarteiraMovimentoEntity;
import com.example.finance.models.entities.dto.CarteiraMovimentoDto;
import com.example.finance.models.entities.enums.TIPOMOVIMENTO;
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
		return carteira;}

	public CarteiraEntity createCarteira(CarteiraMovimentoDto carteiraMovimentoDto) {
		CarteiraEntity carteiraEntity = CarteiraEntity.builder()
		                                             .data(LocalDateTime.now())
		                                             .valor(BigDecimal.ZERO)
		                                              .entidade(null)
		                                              .user(carteiraMovimentoDto.getUser())
		                                             .id(0).build();
		return carteiraRepository.save(carteiraEntity);
	}

	public void deleteCarteira(long id) {
		carteiraRepository.deleteById(id);
	}


	public CarteiraEntity postCarteiraMovimento(CarteiraMovimentoDto carteiraMovimentoDto) {
		CarteiraEntity carteiraEntity = carteiraRepository.findById(carteiraMovimentoDto.getId())
		                                                  .orElseThrow(() -> new RuntimeException("Carteira not found"));

		carteiraEntity.setValor(carteiraEntity.getValor().add(carteiraMovimentoDto.getValor()));

		CarteiraMovimentoEntity carteiraMovimentoEntity = CarteiraMovimentoEntity.builder()
		                                                                         .carteira(carteiraEntity)
		                                                                         .data(LocalDateTime.now())
		                                                                         .tipo(carteiraMovimentoDto.getValor().compareTo(BigDecimal.ZERO) > 0 ? TIPOMOVIMENTO.ENTRADA : TIPOMOVIMENTO.SAIDA)
		                                                                         .valor(carteiraMovimentoDto.getValor())
		                                                                         .venda(carteiraMovimentoDto.getVenda())
		                                                                         .id(0L)
		                                                                         .build();

		carteiraEntity.getCarteiraMovimentoEntity().add(carteiraMovimentoEntity);
		return carteiraRepository.save(carteiraEntity);
	}


}
