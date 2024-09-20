package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.dto;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedReservationDTO {

	public static final String RESERVATION_ID_NAME_FIELD = "reservationId";

	private String reservationId;

	public HttpHeaders getHttpHeader() {
		var header = new HttpHeaders();
		header.set(RESERVATION_ID_NAME_FIELD, reservationId);
		return header;
	}
	
}
