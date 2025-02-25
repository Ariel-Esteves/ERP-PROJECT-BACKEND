package com.example.finance.controllers;

import com.example.finance.Repositories.UserRepository;
import com.example.finance.models.entities.UserEntity;
import com.example.finance.models.entities.dto.AuthenticationDto;
import com.example.finance.models.entities.dto.LoginResponseDto;
import com.example.finance.models.entities.dto.RegisterDto;
import com.example.finance.services.TokenService;
import com.example.finance.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final UserService userService;

	private final TokenService tokenService;
	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService){
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto authenticationDTO) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDto(token));
	}
	

	@PostMapping("/register")
	
	public ResponseEntity<RegisterDto> register(@RequestBody @Valid RegisterDto registerDTO) throws IllegalAccessException {
		return ResponseEntity.ok(userService.register(registerDTO));
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> findUser(@PathVariable long id){
		return ResponseEntity.ok(userService.findByUserId(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
