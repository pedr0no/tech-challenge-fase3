package br.com.fiap.techchallenge.tablereservation.application.gateways;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Reservation;
import br.com.fiap.techchallenge.tablereservation.domain.enums.ReservationStatus;

public interface ReservationGateway {

	Reservation createReservation(Reservation reservation);

	Reservation searchReservationById(String idReservation);

	List<Reservation> searchReservationsByDate(LocalDate date, String restaurantName);

	Reservation updateReservationStatus(String idReservation, ReservationStatus status);

}