package com.example.finance.infra;

import com.example.finance.models.entities.dto.ExceptionDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionDTO> handleConstraintViolationException(ConstraintViolationException e) {

		ExceptionDTO exceptionDTO = new ExceptionDTO("Violação de integridade referêncial", "400", e.getMessage());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	public ResponseEntity<ExceptionDTO> handleNullPointerException(NullPointerException e) {
		
		ExceptionDTO exceptionDTO = new ExceptionDTO("Campo nulo encontrado", "400", e.getMessage());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		
		ExceptionDTO exceptionDTO = new ExceptionDTO("Erro de leitura da requisição", "400", e.getMessage());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ExceptionDTO> sqlIntegrityConstraintViolationException(HttpMessageNotReadableException e) {
		
		ExceptionDTO exceptionDTO = new ExceptionDTO("Chave primaria duplicada", "400", e.getMessage());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<ExceptionDTO> unexpectedTypeException(HttpMessageNotReadableException e) {
		
		ExceptionDTO exceptionDTO = new ExceptionDTO("erro inexperado na validação", "400", e.getMessage());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	@ExceptionHandler(ClassNotFoundException.class)
	public ResponseEntity<ExceptionDTO> classNotFoundException(HttpMessageNotReadableException e) {
		
		ExceptionDTO exceptionDTO = new ExceptionDTO("Registro não encontrado", "400", e.getMessage());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<ExceptionDTO> illegalAccessException(HttpMessageNotReadableException e) {
		
		ExceptionDTO exceptionDTO = new ExceptionDTO("Acesso ilegal", "400", e.getMessage());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
}
