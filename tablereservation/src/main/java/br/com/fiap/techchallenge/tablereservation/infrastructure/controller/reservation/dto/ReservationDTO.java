package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

	@NotBlank
	private String restaurantName;
	@NotBlank
	private String clientEmail;
	@NotNull
	private LocalTime timeReservation;
	@NotNull
	private LocalDate dayOfReservation;
	@Min(value = 1, message = "Quantity of People should be greater than 1")
	private Integer quantityOfPeople;

}
