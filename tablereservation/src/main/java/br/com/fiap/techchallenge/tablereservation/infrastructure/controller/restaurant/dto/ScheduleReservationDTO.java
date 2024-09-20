package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleReservationDTO {
	
	private LocalTime hour;

}
