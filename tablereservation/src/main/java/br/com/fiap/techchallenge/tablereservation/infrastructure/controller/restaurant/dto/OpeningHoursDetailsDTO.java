package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class OpeningHoursDetailsDTO {

	@NotNull
	private LocalDate day;
	private LocalTime openingTime;
	private LocalTime closingTime;
	private List<ScheduleReservationDTO> scheduleOfReservations;

}
