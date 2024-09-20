package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.dto;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedAvaliationDTO {
	
	public static final String AVALIATION_NAME_FIELD = "avaliationId";

	private String idAvaliation;
	
	public HttpHeaders getHttpHeader() {
		var header = new HttpHeaders();
		header.set(AVALIATION_NAME_FIELD, idAvaliation);
		return header;
	}
	

}
