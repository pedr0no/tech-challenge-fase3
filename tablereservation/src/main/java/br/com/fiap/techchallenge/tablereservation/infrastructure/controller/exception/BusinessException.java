package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4667178818031442807L;
	private HttpStatus code;
	private String message;
	
	
	
}
