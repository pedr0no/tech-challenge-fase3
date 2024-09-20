package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.dto.ErrorDTO;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	ResponseEntity<ErrorDTO> handleBusinessException(BusinessException e) {
		return ResponseEntity.status(e.getCode()).body(new ErrorDTO(e.getCode().value(), e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		var stringBuilder = new StringBuilder();
		e.getFieldErrors().forEach(error -> {
			stringBuilder.append(error.getField());
			stringBuilder.append(" ");
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(),
				"Campos obrigatórios não enviados " + stringBuilder.toString()));
	}

}
