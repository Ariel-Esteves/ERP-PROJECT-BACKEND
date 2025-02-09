package com.example.finance.services;

import com.example.finance.Repositories.UserRepository;
import com.example.finance.models.entities.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserEntity createUser(@Valid UserEntity userEntity) {
		UserEntity userSaved = userRepository.save(userEntity);
		return userRepository.save(userSaved);
	}
	public UserDetails findByLogin(String login) {
		return userRepository.findByLogin(login);
	}
	public UserEntity findByUserId(long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}
}
