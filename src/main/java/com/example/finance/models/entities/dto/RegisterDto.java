package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.enums.USERROLE;

public record RegisterDto(String login, String password, USERROLE role) {
	@Override
	public USERROLE role() {
		return USERROLE.USER;
	}
	
	public void validate() throws IllegalAccessException {
		if (login == null || login.isBlank() || password == null || password.isBlank()) {
			throw new IllegalAccessException("Invalid data");
		}
	}
}
