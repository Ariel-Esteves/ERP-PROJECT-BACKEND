package com.example.finance.infra;

import com.example.finance.models.entities.dto.ExceptionDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionDTO> handleConstraintViolationException(ConstraintViolationException e) {

		ExceptionDTO exceptionDTO = new ExceptionDTO("Campo nulo encontrado", "400", e.getMessage());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
}
