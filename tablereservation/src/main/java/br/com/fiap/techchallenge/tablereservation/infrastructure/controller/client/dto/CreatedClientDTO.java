package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.dto;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedClientDTO {

	public static final String CLIENT_ID_NAME_FIELD = "clientId";

	private String clientId;
	
	public HttpHeaders getHttpHeader() {
		var header = new HttpHeaders();
		header.set(CLIENT_ID_NAME_FIELD, clientId);
		return header;
	}
	
}
