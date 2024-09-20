package br.com.fiap.techchallenge.tablereservation.application.usecases;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.fiap.techchallenge.tablereservation.application.gateways.EmailGateway;
import br.com.fiap.techchallenge.tablereservation.application.gateways.ReservationGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Reservation;
import br.com.fiap.techchallenge.tablereservation.domain.enums.ReservationStatus;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;

public class ReservationOperationsCollection {

	private final ReservationGateway reservationGateway;
	private final EmailGateway emailGateway;

	public ReservationOperationsCollection(ReservationGateway reservationGateway, EmailGateway emailGateway) {
		this.reservationGateway = reservationGateway;
		this.emailGateway = emailGateway;
	}

	public Reservation createReservation(Reservation reservation) {
		if (LocalDate.now().equals(reservation.getDayOfReservation())) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "Não pode marcar uma reserva na data de hoje");
		}
		var createReservation = reservationGateway.createReservation(reservation);
		emailGateway.sendEmail(createReservation.getClientEmail(), "Reserva Agendada", createTextToEmail(reservation));
		return createReservation;
	}

	public Reservation searchReservationById(String idReservation) {
		return reservationGateway.searchReservationById(idReservation);
	}

	public List<Reservation> searchReservationByDate(LocalDate date, String restaurantName) {
		return reservationGateway.searchReservationsByDate(date, restaurantName);
	}

	public void updateReservationStatus(String idReservation, ReservationStatus status) {
		var reservation = reservationGateway.updateReservationStatus(idReservation, status);
		if(status.equals(ReservationStatus.FINISHED)) {
			emailGateway.sendEmail(reservation.getClientEmail(), "Reserva Finalizada", "Avalie como foi sua experiência no restaurante em que marcou sua reserva");
		} else {
			emailGateway.sendEmail(reservation.getClientEmail(), "Reserva Cancelada", "Obrigado por ter usado o nosso sistema de reserva");
		}
	}

	private String createTextToEmail(Reservation reservation) {
		return "Restaurante: " + reservation.getRestaurantName() + " Dia da Reserva: "
				+ reservation.getDayOfReservation().toString() + " Horário da Reserva: "
				+ reservation.getTimeReservation().toString();
	}

}