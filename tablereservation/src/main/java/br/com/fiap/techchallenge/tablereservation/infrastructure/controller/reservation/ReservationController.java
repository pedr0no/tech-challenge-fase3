package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.techchallenge.tablereservation.application.usecases.ReservationOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.enums.ReservationStatus;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.dto.ReservationDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.mapper.ReservationMapper;
import br.com.fiap.techchallenge.tablereservation.main.Utils;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	private final ReservationOperationsCollection reservationOperationsCollection;
	private final ReservationMapper reservationMapper;

	public ReservationController(ReservationOperationsCollection reservationOperationsCollection,
			ReservationMapper reservationMapper) {
		this.reservationOperationsCollection = reservationOperationsCollection;
		this.reservationMapper = reservationMapper;
	}

	@PostMapping
	ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO body) {
		var reservation = reservationMapper.reservationDTOToReservation(body);
		return ResponseEntity.created(Utils.getLocation("/reservation")).headers(reservationMapper
				.reservationToCreatedReservationDTO(reservationOperationsCollection.createReservation(reservation))
				.getHttpHeader()).build();
	}

	@GetMapping("/{idReservation}")
	ResponseEntity<ReservationDTO> searchReservationById(@PathVariable String idReservation) {
		return ResponseEntity.ok(reservationMapper
				.reservationToReservationDTO(reservationOperationsCollection.searchReservationById(idReservation)));
	}

	@GetMapping()
	ResponseEntity<List<ReservationDTO>> searchReservationsByDate(@RequestParam(required = true) LocalDate date,
			@RequestParam(required = true) String restaurantName) {
		return ResponseEntity.ok(reservationMapper.listReservationsToListReservationDTO(
				reservationOperationsCollection.searchReservationByDate(date, restaurantName)));
	}

	@PutMapping("status/{idReservation}")
	ResponseEntity<Void> updateReservationStatus(@PathVariable String idReservation,
			@RequestParam ReservationStatus status) {
		reservationOperationsCollection.updateReservationStatus(idReservation, status);
		return ResponseEntity.noContent().build();
	}

}
