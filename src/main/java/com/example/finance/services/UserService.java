package com.example.finance.services;

import com.example.finance.Repositories.UserRepository;
import com.example.finance.models.entities.UserEntity;
import com.example.finance.models.entities.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Autowired
	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public RegisterDto register(RegisterDto registerDTO) throws IllegalAccessException {
			registerDTO.validate();
		String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
		UserEntity newUser = new UserEntity(registerDTO.login(), encryptedPassword, registerDTO.role());
		this.userRepository.save(newUser);
		
		return new RegisterDto(newUser.getLogin(), "****", newUser.getRole());
	}
	
	public UserDetails findByLogin(String login) {
		return userRepository.findByLogin(login);
	}
	
	public UserEntity findByUserName(String login) {
		return userRepository.findUserEntityByLogin(login);
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
