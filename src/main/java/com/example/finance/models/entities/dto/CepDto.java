package com.example.finance.models.entities.dto;

public record CepDto(
		String cep,
		String logradouro,
		String complemento,
		String unidade,
		String bairro,
		String localidade,
		String uf,
		String estado,
		String regiao,
		String ibge,
		String gia,
		String ddd,
		String siafi
) {}