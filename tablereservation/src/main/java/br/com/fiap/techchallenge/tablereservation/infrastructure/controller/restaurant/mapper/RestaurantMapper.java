package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Localization;
import br.com.fiap.techchallenge.tablereservation.domain.entity.OpeningHoursDetails;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Restaurant;
import br.com.fiap.techchallenge.tablereservation.domain.entity.ScheduleReservation;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Table;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto.CreatedRestaurantDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto.LocalizationDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto.OpeningHoursDetailsDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto.RestaurantDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto.ScheduleReservationDTO;
import br.com.fiap.techchallenge.tablereservation.main.Utils;

public class RestaurantMapper {

	// Métodos que mapeiam de Entity para DTO

	public CreatedRestaurantDTO restaurantToCreatedResponseDTO(Restaurant restaurant) {
		return new CreatedRestaurantDTO(restaurant.getId());
	}

	public RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant) {
		return new RestaurantDTO(restaurant.getName(), restaurant.getCuisine(), restaurant.getQuantityPeoplePerTables(),
				restaurant.getQuantityOfTables(), mapLocalizationDTO(restaurant),
				mapOpeningHoursDetailsDTO(restaurant));
	}

	private List<OpeningHoursDetailsDTO> mapOpeningHoursDetailsDTO(Restaurant restaurant) {
		var openingHoursDetailsDTO = new ArrayList<OpeningHoursDetailsDTO>();
		restaurant.getOpeningHours().forEach(op -> {
			var openingHoursDetailDTO = new OpeningHoursDetailsDTO();

			openingHoursDetailDTO.setDay(op.getDay());
			openingHoursDetailDTO.setOpeningTime(op.getOpeningTime());
			openingHoursDetailDTO.setClosingTime(op.getClosingTime());

			var scheduleOfReservations = mapScheduleReservationDTO(op);

			openingHoursDetailDTO.setScheduleOfReservations(scheduleOfReservations);
			openingHoursDetailsDTO.add(openingHoursDetailDTO);
		});
		return openingHoursDetailsDTO;
	}

	private List<ScheduleReservationDTO> mapScheduleReservationDTO(OpeningHoursDetails op) {
		var scheduleOfReservations = new ArrayList<ScheduleReservationDTO>();
		op.getScheduleOfReservation().forEach(sR -> {
			var scheduleOfReservation = new ScheduleReservationDTO();
			scheduleOfReservation.setHour(sR.getHour());
			scheduleOfReservations.add(scheduleOfReservation);
		});
		return scheduleOfReservations;
	}

	private LocalizationDTO mapLocalizationDTO(Restaurant restaurant) {
		return new LocalizationDTO(restaurant.getLocalization().getStreet(), restaurant.getLocalization().getNumber(),
				restaurant.getLocalization().getNeighborhood(), restaurant.getLocalization().getCity(),
				restaurant.getLocalization().getCep());
	}

	// Métodos que mapeiam de DTO para Entity

	public Restaurant restaurantDTOToRestaurant(RestaurantDTO restaurantDTO) {
		return new Restaurant(restaurantDTO.getName(), mapLocalization(restaurantDTO), restaurantDTO.getCuisine(),
				restaurantDTO.getQuantityPeoplePerTables(), restaurantDTO.getQuantityOfTables(),
				mapOpeningHoursDetails(restaurantDTO));
	}

	public Localization mapLocalization(RestaurantDTO restaurantDTO) {
		var localization = new Localization();
		if (Objects.nonNull(restaurantDTO.getLocalization())) {
			localization.setStreet(restaurantDTO.getLocalization().getStreet());
			localization.setNumber(restaurantDTO.getLocalization().getNumber());
			localization.setNeighborhood(restaurantDTO.getLocalization().getNeighborhood());
			localization.setCity(restaurantDTO.getLocalization().getCity());
			localization.setCep(restaurantDTO.getLocalization().getCep());
		}
		return localization;
	}

	public List<OpeningHoursDetails> mapOpeningHoursDetails(RestaurantDTO restaurantDTO) {
		var openingHoursDetails = new ArrayList<OpeningHoursDetails>();
		if (Utils.isValidList(restaurantDTO.getOpeningHours())) {
			restaurantDTO.getOpeningHours().forEach(ohd -> {
				var openingHoursDetail = new OpeningHoursDetails();

				openingHoursDetail.setDay(ohd.getDay());
				openingHoursDetail.setOpeningTime(ohd.getOpeningTime());
				openingHoursDetail.setClosingTime(ohd.getClosingTime());

				var scheduleOfReservations = mapScheduleReservation(restaurantDTO, ohd);

				openingHoursDetail.setScheduleOfReservation(scheduleOfReservations);
				openingHoursDetails.add(openingHoursDetail);
			});
		}
		return openingHoursDetails;
	}

	private List<ScheduleReservation> mapScheduleReservation(RestaurantDTO restaurantDTO, OpeningHoursDetailsDTO ohd) {
		var scheduleOfReservations = new ArrayList<ScheduleReservation>();
		if (Utils.isValidList(ohd.getScheduleOfReservations())) {
			ohd.getScheduleOfReservations().forEach(sR -> {
				var scheduleOfReservation = new ScheduleReservation();
				scheduleOfReservation.setHour(sR.getHour());
				var tables = new ArrayList<Table>();
				for (int i = 0; i <= restaurantDTO.getQuantityOfTables(); i++) {
					var table = new Table();
					tables.add(table);
				}
				scheduleOfReservation.setTables(tables);
				scheduleOfReservations.add(scheduleOfReservation);
			});
		}
		return scheduleOfReservations;
	}

}
