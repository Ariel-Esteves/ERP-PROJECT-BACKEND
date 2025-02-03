package com.example.finance.services;

import com.example.finance.Repositories.CarteiraRepository;
import com.example.finance.Repositories.UserRepository;
import com.example.finance.models.entities.CarteiraEntity;
import com.example.finance.models.entities.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final CarteiraRepository carteiraRepository;

	@Autowired
	UserService(UserRepository userRepository, CarteiraRepository carteiraRepository) {
		this.userRepository = userRepository;
		this.carteiraRepository = carteiraRepository;
	}

	public UserEntity createUser(@Valid UserEntity userEntity) {
		UserEntity userSaved = userRepository.save(userEntity);
		// create user
		CarteiraEntity carteira = CarteiraEntity.builder()
		                                        .entidade(null)
		                                        .data(LocalDateTime.now())
		                                        .valor(BigDecimal.ZERO)
		                                        .carteiraMovimentoEntity(new ArrayList<>())
		                                        .user(userSaved)
		                                        .id(0)
		                                        .build();

		CarteiraEntity carteiraSaved = carteiraRepository.save(carteira);

		userEntity.setCarteira(carteiraSaved);

		return userRepository.save(userSaved);
	}

	public UserEntity fincUser(long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}
}
