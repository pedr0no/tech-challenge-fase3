package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Reservation;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.dto.CreatedReservationDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.dto.ReservationDTO;
import br.com.fiap.techchallenge.tablereservation.main.Utils;

public class ReservationMapper {

	// Método que mapeiam de DTO para Domain

	public Reservation reservationDTOToReservation(ReservationDTO createReservationDTO) {
		return new Reservation(createReservationDTO.getRestaurantName(), createReservationDTO.getClientEmail(),
				createReservationDTO.getTimeReservation(), createReservationDTO.getDayOfReservation(),
				createReservationDTO.getQuantityOfPeople());
	}

	// Métodos que mapeiam de Domain para DTO

	public CreatedReservationDTO reservationToCreatedReservationDTO(Reservation reservation) {
		return new CreatedReservationDTO(reservation.getId());
	}

	public ReservationDTO reservationToReservationDTO(Reservation reservation) {
		return new ReservationDTO(reservation.getRestaurantName(), reservation.getClientEmail(),
				reservation.getTimeReservation(), reservation.getDayOfReservation(), reservation.getQuantityOfPeople());
	}

	public List<ReservationDTO> listReservationsToListReservationDTO(List<Reservation> reservations) {
		if(Utils.isValidList(reservations)) {
			var reservationsDTO = new ArrayList<ReservationDTO>();
			reservations.forEach(reservation -> {
				var reservationDTO = new ReservationDTO(reservation.getRestaurantName(), reservation.getClientEmail(),
						reservation.getTimeReservation(), reservation.getDayOfReservation(),
						reservation.getQuantityOfPeople());
				reservationsDTO.add(reservationDTO);
			});
			return reservationsDTO;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "Não há nenhuma reserva no dia informado");
	}

}
