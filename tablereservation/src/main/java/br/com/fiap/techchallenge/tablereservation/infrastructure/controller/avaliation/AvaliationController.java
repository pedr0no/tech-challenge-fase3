package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.techchallenge.tablereservation.application.usecases.AvaliationOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.dto.AvaliationDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.mapper.AvaliationMapper;
import br.com.fiap.techchallenge.tablereservation.main.Utils;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliation")
public class AvaliationController {

	private final AvaliationOperationsCollection avaliationOperationsCollection;
	private final AvaliationMapper avaliationMapper;

	public AvaliationController(AvaliationOperationsCollection avaliationOperationsCollection,
			AvaliationMapper avaliationMapper) {
		this.avaliationOperationsCollection = avaliationOperationsCollection;
		this.avaliationMapper = avaliationMapper;
	}

	@PostMapping
	ResponseEntity<Void> createAvaliation(@RequestBody @Valid AvaliationDTO avaliation) {
		return ResponseEntity.created(Utils.getLocation("/avaliation"))
				.headers(avaliationMapper
						.avaliationToCreatedAvaliationDTO(avaliationOperationsCollection
								.createAvaliation(avaliationMapper.avaliationDTOToAvaliation(avaliation)))
						.getHttpHeader())
				.build();
	}

	@GetMapping("/{idAvaliation}")
	ResponseEntity<AvaliationDTO> searchAvaliationById(@PathVariable String idAvaliation) {
		return ResponseEntity.ok(avaliationMapper
				.avaliationToAvaliationDTO(avaliationOperationsCollection.searchAvaliationById(idAvaliation)));
	}

	@GetMapping
	ResponseEntity<List<AvaliationDTO>> searchAvaliationsForRestaurant(@RequestParam String restaurantName) {
		return ResponseEntity.ok(avaliationMapper.listAvaliationToListAvaliationDTO(
				avaliationOperationsCollection.searchAvaliationsForRestaurant(restaurantName)));
	}

}
