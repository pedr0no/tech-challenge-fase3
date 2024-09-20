package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
	
	@NotBlank
	private String name;
	@NotBlank
	private String cuisine;
	@Min(value = 1, message = "Quantity People per Table should be greater than 1") 
	private int quantityPeoplePerTables;
	@Min(value = 1, message = "Quantity of Tables should be greater than 1") 
	private int quantityOfTables;
	@Valid
	private LocalizationDTO localization; 
	@Valid
	private List<OpeningHoursDetailsDTO> openingHours;

}
