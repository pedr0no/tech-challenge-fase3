package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedRestaurantDTO {

	public static final String RESTAURANT_ID_NAME_FIELD = "restaurantId";

	private String restaurantId;

	public HttpHeaders getHttpHeader() {
		var header = new HttpHeaders();
		header.set(RESTAURANT_ID_NAME_FIELD, restaurantId);
		return header;
	}
}
