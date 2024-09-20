package br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Localization;
import br.com.fiap.techchallenge.tablereservation.domain.entity.OpeningHoursDetails;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Restaurant;
import br.com.fiap.techchallenge.tablereservation.domain.entity.ScheduleReservation;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.LocalizationEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.OpeningHoursDetailsEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.ScheduleReservationEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.TableEntity;
import br.com.fiap.techchallenge.tablereservation.main.Utils;

public class RestaurantEntityMapper {

	// Métodos que mapeiam de Entity para Domain

	public Restaurant entityToRestaurant(RestaurantEntity entity) {
		var restaurant = new Restaurant();
		restaurant.setId(entity.getId());
		restaurant.setName(entity.getName());
		restaurant.setCuisine(entity.getCuisine());
		restaurant.setQuantityPeoplePerTables(entity.getQuantityPeoplePerTables());
		restaurant.setQuantityOfTables(entity.getQuantityOfTables());
		restaurant.setLocalization(mapLocalization(entity));
		restaurant.setOpeningHours(mapOpeningHours(entity));
		return restaurant;
	}

	public Restaurant listEntityToRestaurant(List<RestaurantEntity> entities, Integer number, String cep) {
		if (Utils.isValidList(entities)) {
			RestaurantEntity restaurantEntity = entities.stream()
					.filter(entity -> entity.getLocalization().getCep().equals(cep))
					.filter(entity -> entity.getLocalization().getNumber().equals(number)).findFirst()
					.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
							"Não foi possível achar um restaurante com o número informado"));
			return entityToRestaurant(restaurantEntity);
		}
		throw new BusinessException(HttpStatus.NOT_FOUND, "Não foi possível achar um restaurante com o CEP informado");
	}

	private Localization mapLocalization(RestaurantEntity entity) {
		var localization = new Localization();
		localization.setStreet(entity.getLocalization().getStreet());
		localization.setNumber(entity.getLocalization().getNumber());
		localization.setNeighborhood(entity.getLocalization().getNeighborhood());
		localization.setCity(entity.getLocalization().getCity());
		localization.setCep(entity.getLocalization().getCep());
		return localization;
	}

	private ArrayList<OpeningHoursDetails> mapOpeningHours(RestaurantEntity entity) {
		var openingHours = new ArrayList<OpeningHoursDetails>();
		entity.getOpeningHours().forEach(op -> {
			var openingHour = new OpeningHoursDetails();
			openingHour.setDay(LocalDate.parse(op.getDay()));
			openingHour
					.setOpeningTime(Objects.nonNull(op.getOpeningTime()) ? LocalTime.parse(op.getOpeningTime()) : null);
			openingHour
					.setClosingTime(Objects.nonNull(op.getClosingTime()) ? LocalTime.parse(op.getClosingTime()) : null);
			var schedules = new ArrayList<ScheduleReservation>();
			if (Utils.isValidList(op.getScheduleOfReservation())) {
				op.getScheduleOfReservation().forEach(scheduleEntity -> {
					var schedule = new ScheduleReservation();
					schedule.setHour(LocalTime.parse(scheduleEntity.getHour()));
					schedules.add(schedule);
				});
			}
			openingHour.setScheduleOfReservation(schedules);
			openingHours.add(openingHour);
		});
		return openingHours;
	}

	// Métodos que mapeiam de Domain para Entity

	public RestaurantEntity restaurantToRestaurantEntity(Restaurant restaurant) {
		var entity = new RestaurantEntity();
		entity.setName(restaurant.getName());
		entity.setCuisine(restaurant.getCuisine());
		entity.setQuantityPeoplePerTables(restaurant.getQuantityPeoplePerTables());
		entity.setQuantityOfTables(restaurant.getQuantityOfTables());
		var localizationEntity = mapLocalizationEntity(restaurant);
		entity.setLocalization(localizationEntity);
		var openingHoursEntity = mapOpeningHoursEntity(restaurant);
		entity.setOpeningHours(openingHoursEntity);
		return entity;
	}

	private ArrayList<OpeningHoursDetailsEntity> mapOpeningHoursEntity(Restaurant restaurant) {
		var entityOpeningHours = new ArrayList<OpeningHoursDetailsEntity>();
		if (Utils.isValidList(restaurant.getOpeningHours())) {
			restaurant.getOpeningHours().forEach(openingHour -> {
				var entityOpeningHour = new OpeningHoursDetailsEntity();
				entityOpeningHour.setDay(openingHour.getDay().toString());
				entityOpeningHour.setOpeningTime(
						Objects.nonNull(openingHour.getOpeningTime()) ? openingHour.getOpeningTime().toString() : null);
				entityOpeningHour.setClosingTime(
						Objects.nonNull(openingHour.getClosingTime()) ? openingHour.getClosingTime().toString() : null);
				var schedulesEntity = new ArrayList<ScheduleReservationEntity>();
				if (Utils.isValidList(openingHour.getScheduleOfReservation())) {
					openingHour.getScheduleOfReservation().forEach(schedule -> {
						var scheduleEntity = new ScheduleReservationEntity();
						scheduleEntity.setHour(schedule.getHour().toString());
						var tablesEntity = new ArrayList<TableEntity>();
						schedule.getTables().forEach(t -> {
							var tableEntity = new TableEntity();
							tableEntity.setStatus(t.getStatus());
							tablesEntity.add(tableEntity);
						});
						scheduleEntity.setTables(tablesEntity);
						schedulesEntity.add(scheduleEntity);
					});
				}
				entityOpeningHour.setScheduleOfReservation(schedulesEntity);
				entityOpeningHours.add(entityOpeningHour);
			});
		}
		return entityOpeningHours;
	}

	private LocalizationEntity mapLocalizationEntity(Restaurant restaurant) {
		var localizationEntity = new LocalizationEntity();
		if (Objects.nonNull(restaurant.getLocalization())) {
			localizationEntity.setCep(restaurant.getLocalization().getCep());
			localizationEntity.setCity(restaurant.getLocalization().getCity());
			localizationEntity.setNeighborhood(restaurant.getLocalization().getNeighborhood());
			localizationEntity.setNumber(restaurant.getLocalization().getNumber());
			localizationEntity.setStreet(restaurant.getLocalization().getStreet());
		}
		return localizationEntity;
	}

	// Método para atualização de Entidade

	public void outdatedRestaurantToUpdatedRestaurant(RestaurantEntity updatedRestaurant,
			RestaurantEntity outdatedRestaurant) {
		if (Objects.nonNull(updatedRestaurant.getName()))
			outdatedRestaurant.setName(updatedRestaurant.getName());
		if (Objects.nonNull(updatedRestaurant.getCuisine()))
			outdatedRestaurant.setCuisine(updatedRestaurant.getCuisine());
		if (Objects.nonNull(updatedRestaurant.getQuantityPeoplePerTables()))
			outdatedRestaurant.setQuantityPeoplePerTables(updatedRestaurant.getQuantityPeoplePerTables());
		validateLocalization(updatedRestaurant, outdatedRestaurant);
		validateOpeningHours(updatedRestaurant, outdatedRestaurant);
	}

	private void validateLocalization(RestaurantEntity updatedRestaurant, RestaurantEntity outdatedRestaurant) {
		if (Objects.nonNull(updatedRestaurant.getLocalization())) {
			if (Objects.nonNull(updatedRestaurant.getLocalization().getStreet()))
				outdatedRestaurant.getLocalization().setStreet(updatedRestaurant.getLocalization().getStreet());
			if (Objects.nonNull(updatedRestaurant.getLocalization().getCity()))
				outdatedRestaurant.getLocalization().setCity(updatedRestaurant.getLocalization().getCity());
			if (Objects.nonNull(updatedRestaurant.getLocalization().getNumber()))
				outdatedRestaurant.getLocalization().setNumber(updatedRestaurant.getLocalization().getNumber());
			if (Objects.nonNull(updatedRestaurant.getLocalization().getNeighborhood()))
				outdatedRestaurant.getLocalization()
						.setNeighborhood(updatedRestaurant.getLocalization().getNeighborhood());
			if (Objects.nonNull(updatedRestaurant.getLocalization().getCep()))
				outdatedRestaurant.getLocalization().setCep(updatedRestaurant.getLocalization().getCep());
		}
	}

	private void validateOpeningHours(RestaurantEntity updatedRestaurant, RestaurantEntity outdatedRestaurant) {
		if (Utils.isValidList(updatedRestaurant.getOpeningHours())) {
			updatedRestaurant.getOpeningHours().forEach(op -> {
				outdatedRestaurant.getOpeningHours().forEach(oop -> {
					if (Objects.nonNull(op.getDay()))
						oop.setDay(op.getDay());
					if (Objects.nonNull(op.getOpeningTime()))
						oop.setOpeningTime(op.getOpeningTime());
					if (Objects.nonNull(op.getClosingTime()))
						oop.setClosingTime(op.getClosingTime());
					if (Utils.isValidList(op.getScheduleOfReservation())) {
						op.getScheduleOfReservation().forEach(sr -> {
							oop.getScheduleOfReservation().forEach(osr -> {
								if (Objects.nonNull(sr.getHour()))
									sr.setHour(osr.getHour());
								if (Objects.nonNull(sr.getTables()))
									osr.setTables(sr.getTables());
							});
						});
					}
				});
			});
			outdatedRestaurant.setOpeningHours(updatedRestaurant.getOpeningHours());
		}
	}
}
