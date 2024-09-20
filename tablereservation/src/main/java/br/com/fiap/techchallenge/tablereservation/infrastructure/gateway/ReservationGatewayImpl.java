package br.com.fiap.techchallenge.tablereservation.infrastructure.gateway;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.techchallenge.tablereservation.application.gateways.ReservationGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Reservation;
import br.com.fiap.techchallenge.tablereservation.domain.enums.ReservationStatus;
import br.com.fiap.techchallenge.tablereservation.domain.enums.TableStatus;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper.ReservationEntityMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.ClientRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.ReservationRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.RestaurantRepository;

public class ReservationGatewayImpl implements ReservationGateway {

	private final RestaurantRepository restaurantRepository;
	private final ClientRepository clientRepository;
	private final ReservationRepository reservationRepository;
	private final ReservationEntityMapper reservationEntityMapper;

	public ReservationGatewayImpl(RestaurantRepository restaurantRepository, ClientRepository clientRepository,
			ReservationEntityMapper reservationEntityMapper, ReservationRepository reservationRepository) {
		this.restaurantRepository = restaurantRepository;
		this.clientRepository = clientRepository;
		this.reservationRepository = reservationRepository;
		this.reservationEntityMapper = reservationEntityMapper;
	}

	@Override
	@Transactional
	public Reservation createReservation(Reservation reservation) {
		var restaurant = restaurantRepository.findByName(reservation.getRestaurantName());
		var client = clientRepository.findByEmail(reservation.getClientEmail());
		var entity = reservationEntityMapper.buildReservationEntity(restaurant, client, reservation);
		restaurant.getReservations().add(entity);
		var entitySaved = reservationRepository.save(entity);
		restaurantRepository.save(restaurant);
		return reservationEntityMapper.reservationEntityToReservation(entitySaved);
	}

	@Override
	public Reservation searchReservationById(String idReservation) {
		var entity = reservationRepository.findById(idReservation)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
						"Não foi possível achar uma Reserva com o Id informado"));
		return reservationEntityMapper.reservationEntityToReservation(entity);
	}

	@Override
	public List<Reservation> searchReservationsByDate(LocalDate date, String restaurantName) {
		var restaurant = restaurantRepository.findByName(restaurantName);
		return reservationEntityMapper.listReservationsEntityToListReservation(restaurant, date);
	}

	@Override
	@Transactional
	public Reservation updateReservationStatus(String idReservation, ReservationStatus status) {
		var entity = reservationRepository.findById(idReservation)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
						"Não foi possível achar uma Reserva com o Id informado"));

		var openingHour = reservationEntityMapper.catchOpeningHourByDay(entity.getRestaurant(), entity);
		var schedule = reservationEntityMapper.catchScheduleReservationByHour(entity, openingHour);
		var tables = reservationEntityMapper.catchTablesByStatus(schedule, TableStatus.RESERVERD);
		reservationEntityMapper.liberateTablesByQuantityTableToLiberate(entity.getQuantityOfReservedTables(), tables);
		entity.setReservationStatus(status);
		restaurantRepository.save(entity.getRestaurant());
		var entitySaved = reservationRepository.save(entity);
		// reservationRepository.deleteById(idReservation);
		return reservationEntityMapper.reservationEntityToReservation(entitySaved);
	}

}