package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class LocalizationDTO {

	@NotBlank
	private String street;
	private int number;
	@NotBlank
	private String neighborhood;
	@NotBlank
	private String city;
	@NotBlank
	private String cep;

}
