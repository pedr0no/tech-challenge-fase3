package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
	
	private Integer code;
	private String message;

}
