package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliationDTO {
	
	@NotBlank
	private String clientEmail;
	@NotBlank
	private String restaurantName;
	@NotNull
	@Max(value = 5, message = "O valor máximo é 5")
	@Min(value = 0, message = "O valor mínimo é 0")
	private Double stars;
	private String comment;

}
