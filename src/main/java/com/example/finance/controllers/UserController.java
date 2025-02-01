package com.example.finance.controllers;

import com.example.finance.models.entities.UserEntity;
import com.example.finance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
	private UserService userService;

	@Autowired
	UserController(UserService userService){
		this.userService = userService;
	}
	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity){
		// create user

		return ResponseEntity.ok(userService.createUser(userEntity));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> findUser(@PathVariable long id){
		return ResponseEntity.ok(userService.fincUser(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}


}
