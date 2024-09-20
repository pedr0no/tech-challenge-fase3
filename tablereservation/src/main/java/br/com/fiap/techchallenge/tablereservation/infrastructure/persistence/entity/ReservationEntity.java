package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.fiap.techchallenge.tablereservation.domain.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "reservation")
@Getter
@Setter
public class ReservationEntity {

	@Id
	private String id;
	@DBRef
	private ClientEntity client;
	@DBRef
	private RestaurantEntity restaurant;
	private Integer quantityOfReservedTables;
	private ReservationStatus reservationStatus;
	private String timeOfReservation;
	private String dayOfReservation;

}
