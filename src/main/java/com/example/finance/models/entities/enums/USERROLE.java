package com.example.finance.models.entities.enums;

public enum USERROLE {
	ADMIN("admin"),
	USER("user");

	private String role;

	USERROLE(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}
