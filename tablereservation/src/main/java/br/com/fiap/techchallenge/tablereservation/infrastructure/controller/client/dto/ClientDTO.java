package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
	
	private String name; 
	private String email;

}
