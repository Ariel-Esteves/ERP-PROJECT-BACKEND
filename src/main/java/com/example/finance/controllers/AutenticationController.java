package com.example.finance.controllers;

import com.example.finance.Repositories.UserRepository;
import com.example.finance.models.entities.UserEntity;
import com.example.finance.models.entities.dto.AuthenticationDto;
import com.example.finance.models.entities.dto.LoginResponseDto;
import com.example.finance.models.entities.dto.RegisterDto;
import com.example.finance.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutenticationController {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;

	private final TokenService tokenService;
	@Autowired
	public AutenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService){
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDTO) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDto(token));
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDto registerDTO) {
		if(this.userRepository.findByLogin(registerDTO.login()).isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
		UserEntity newUser = new UserEntity(registerDTO.login(), encryptedPassword, registerDTO.role());
		this.userRepository.save(newUser);

		return ResponseEntity.ok().build();
	}

}
