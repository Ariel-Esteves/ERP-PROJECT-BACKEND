package com.example.finance.Repositories;

import com.example.finance.models.entities.dto.CepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "cep", url = "https://viacep.com.br/ws/")
public interface CepRepository {
	@RequestMapping("{cep}/json/")
	public CepDto getCep(@PathVariable String cep);
}
