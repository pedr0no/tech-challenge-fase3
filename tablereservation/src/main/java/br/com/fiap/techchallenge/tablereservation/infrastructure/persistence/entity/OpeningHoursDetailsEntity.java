package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpeningHoursDetailsEntity {

	private String day;
	private String openingTime;
	private String closingTime;
	private List<ScheduleReservationEntity> scheduleOfReservation;

}