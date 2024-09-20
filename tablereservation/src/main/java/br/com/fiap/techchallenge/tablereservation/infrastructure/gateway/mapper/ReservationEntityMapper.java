package br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Reservation;
import br.com.fiap.techchallenge.tablereservation.domain.enums.ReservationStatus;
import br.com.fiap.techchallenge.tablereservation.domain.enums.TableStatus;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.ClientEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.OpeningHoursDetailsEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.ReservationEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.ScheduleReservationEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.TableEntity;
import br.com.fiap.techchallenge.tablereservation.main.Utils;

public class ReservationEntityMapper {

	// Métodos que mapeiam Entity para Domain

	public Reservation reservationEntityToReservation(ReservationEntity entity) {
		var reservation = new Reservation();
		reservation.setId(entity.getId());
		reservation.setDayOfReservation(LocalDate.parse(entity.getDayOfReservation()));
		reservation.setTimeReservation(LocalTime.parse(entity.getTimeOfReservation()));
		reservation.setQuantityOfPeople(entity.getQuantityOfReservedTables());
		reservation.setClientEmail(entity.getClient().getEmail());
		reservation.setRestaurantName(entity.getRestaurant().getName());
		return reservation;
	}

	public List<Reservation> listReservationsEntityToListReservation(RestaurantEntity restaurant, LocalDate date) {
		if (Utils.isValidList(restaurant.getReservations())) {
			var reservations = new ArrayList<Reservation>();
			List<ReservationEntity> listEntity = restaurant.getReservations().stream()
					.filter(sE -> sE.getDayOfReservation().contains(date.toString())).collect(Collectors.toList());
			listEntity.forEach(entity -> {
				var reservation = reservationEntityToReservation(entity);
				reservations.add(reservation);
			});
			return reservations;
		}
		throw new BusinessException(HttpStatus.NOT_FOUND, "O restaurante informado não possui reservas");
	}

	// Métodos que mapeiam Domain para Entity

	public ReservationEntity buildReservationEntity(RestaurantEntity restaurantEntity, ClientEntity clientEntity,
			Reservation reservation) {
		var tablesToReserve = calculateTablesToReserve(reservation.getQuantityOfPeople(),
				restaurantEntity.getQuantityPeoplePerTables());

		var openingHourChooseByDay = catchOpeningHourByDay(restaurantEntity, reservation);

		var scheduleReservationChooseByTime = catchScheduleReservationByHour(reservation, openingHourChooseByDay);

		List<TableEntity> tablesAvailables = catchTablesByStatus(scheduleReservationChooseByTime,
				TableStatus.AVAILABLE);

		reserveTablesByQuantityTableToReserve(tablesToReserve, tablesAvailables);

		var reservationEntity = createReservationEntity(restaurantEntity, clientEntity, reservation, tablesToReserve);

		return reservationEntity;

	}

	private ReservationEntity createReservationEntity(RestaurantEntity restaurantEntity, ClientEntity clientEntity,
			Reservation reservation, Double tablesToReserve) {
		var reservationEntity = new ReservationEntity();
		reservationEntity.setClient(clientEntity);
		reservationEntity.setRestaurant(restaurantEntity);
		reservationEntity.setQuantityOfReservedTables(tablesToReserve.intValue());
		reservationEntity.setReservationStatus(ReservationStatus.SCHEDULED);
		reservationEntity.setTimeOfReservation(reservation.getTimeReservation().toString());
		reservationEntity.setDayOfReservation(reservation.getDayOfReservation().toString());
		return reservationEntity;
	}

	private void reserveTablesByQuantityTableToReserve(Double tablesToReserve, List<TableEntity> tablesAvailables) {
		if (tablesAvailables.size() >= tablesToReserve) {
			for (int i = 0; i < tablesToReserve; i++) {
				tablesAvailables.get(i).setStatus(TableStatus.RESERVERD);
			}
		} else {
			throw new BusinessException(HttpStatus.NOT_FOUND,
					"Não há mesas disponíveis suficientes para o número de pessoas enviadas");
		}
	}

	public void liberateTablesByQuantityTableToLiberate(Integer tablesToLiberate, List<TableEntity> tablesOccupy) {
		if (tablesOccupy.size() >= tablesToLiberate) {
			for (int i = 0; i < tablesToLiberate; i++) {
				tablesOccupy.get(i).setStatus(TableStatus.AVAILABLE);
			}
		} else {
			throw new BusinessException(HttpStatus.NOT_FOUND, "Não há mesas ocupadas de acordo com o que foi passado");
		}
	}

	public List<TableEntity> catchTablesByStatus(ScheduleReservationEntity scheduleReservationChooseByTime,
			TableStatus status) {
		List<TableEntity> tablesReserved = scheduleReservationChooseByTime.getTables().stream()
				.filter(tb -> tb.getStatus().equals(status)).collect(Collectors.toList());
		return tablesReserved;
	}

	private OpeningHoursDetailsEntity catchOpeningHourByDay(RestaurantEntity restaurantEntity,
			Reservation reservation) {
		var optionalOpeningHourChooseByDay = restaurantEntity.getOpeningHours().stream()
				.filter(oh -> LocalDate.parse(oh.getDay()).equals(reservation.getDayOfReservation())).findFirst();
		var openingHourChooseByDay = optionalOpeningHourChooseByDay
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
						"O restaurante não abriu reservas para o dia informado"));
		return openingHourChooseByDay;
	}

	public OpeningHoursDetailsEntity catchOpeningHourByDay(RestaurantEntity restaurantEntity,
			ReservationEntity reservation) {
		var optionalOpeningHourChooseByDay = restaurantEntity.getOpeningHours().stream()
				.filter(oh -> oh.getDay().equals(reservation.getDayOfReservation())).findFirst();
		var openingHourChooseByDay = optionalOpeningHourChooseByDay
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Não foi achado o dia da sua reserva"));
		return openingHourChooseByDay;
	}

	private ScheduleReservationEntity catchScheduleReservationByHour(Reservation reservation,
			OpeningHoursDetailsEntity openingHourChooseByDay) {
		var optionalScheduleReservationChooseByTime = openingHourChooseByDay.getScheduleOfReservation().stream()
				.filter(sr -> LocalTime.parse(sr.getHour()).equals(reservation.getTimeReservation())).findFirst();
		var scheduleReservationChooseByTime = optionalScheduleReservationChooseByTime
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
						"O restaurante não abriu reservas para o horário informado, ou não há reservas disponíveis nesse horário"));
		return scheduleReservationChooseByTime;
	}

	public ScheduleReservationEntity catchScheduleReservationByHour(ReservationEntity reservation,
			OpeningHoursDetailsEntity openingHourChooseByDay) {
		var optionalScheduleReservationChooseByTime = openingHourChooseByDay.getScheduleOfReservation().stream()
				.filter(sr -> sr.getHour().equals(reservation.getTimeOfReservation())).findFirst();
		var scheduleReservationChooseByTime = optionalScheduleReservationChooseByTime.orElseThrow(
				() -> new BusinessException(HttpStatus.NOT_FOUND, "Não foi achado o horário da sua reserva"));
		return scheduleReservationChooseByTime;
	}

	private Double calculateTablesToReserve(int quantityOfPeople, int quantityPeoplePerTables) {
		var tablesToReserve = (double) (quantityOfPeople / quantityPeoplePerTables);
		return Math.ceil(tablesToReserve);
	}

}
