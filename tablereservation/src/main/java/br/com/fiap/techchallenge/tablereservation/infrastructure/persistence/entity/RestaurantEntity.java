package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "restaurant")
@Getter
@Setter
public class RestaurantEntity {

	@Id
	private String id;
	private String name;
	private LocalizationEntity localization;
	private String cuisine;
	private List<OpeningHoursDetailsEntity> openingHours;
	private int quantityPeoplePerTables;
	private int quantityOfTables;
	@DBRef
	private List<ReservationEntity> reservations = new ArrayList<>();
	@DBRef
	private List<AvaliationEntity> avaliations = new ArrayList<>();
	private Double avaliationAverage;
	
}