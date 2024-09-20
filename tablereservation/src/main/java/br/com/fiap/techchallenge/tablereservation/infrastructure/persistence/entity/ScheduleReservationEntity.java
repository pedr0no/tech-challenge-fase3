package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleReservationEntity {
	
	private String hour;
	private List<TableEntity> tables;

}
