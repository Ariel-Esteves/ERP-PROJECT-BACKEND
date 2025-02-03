package com.example.finance.models.entities.dto;

import com.example.finance.models.entities.enums.USERROLE;

public record RegisterDto(String login, String password, USERROLE role) {}
