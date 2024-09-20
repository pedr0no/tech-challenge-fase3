package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.techchallenge.tablereservation.application.usecases.RestaurantOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto.RestaurantDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.mapper.RestaurantMapper;
import br.com.fiap.techchallenge.tablereservation.main.Utils;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	private final RestaurantOperationsCollection restaurantOperationsCollection;
	private final RestaurantMapper restaurantMapper;

	public RestaurantController(RestaurantOperationsCollection restaurantOperationsCollection,
			RestaurantMapper restaurantMapper) {
		this.restaurantOperationsCollection = restaurantOperationsCollection;
		this.restaurantMapper = restaurantMapper;
	}

	@PostMapping
	ResponseEntity<Void> createRestaurant(@RequestBody @Valid RestaurantDTO body) {
		var restaurant = restaurantMapper.restaurantDTOToRestaurant(body);
		return ResponseEntity.created(Utils.getLocation("restaurant"))
				.headers(restaurantMapper
						.restaurantToCreatedResponseDTO(restaurantOperationsCollection.createRestaurant(restaurant))
						.getHttpHeader())
				.build();
	}

	@PutMapping("/{idRestaurant}")
	ResponseEntity<Void> updateRestaurant(@PathVariable String idRestaurant, @RequestBody RestaurantDTO body) {
		restaurantOperationsCollection.updateRestaurant(idRestaurant, restaurantMapper.restaurantDTOToRestaurant(body));
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{idRestaurant}")
	ResponseEntity<RestaurantDTO> searchRestaurantById(@PathVariable String idRestaurant) {
		return ResponseEntity.ok(restaurantMapper
				.restaurantToRestaurantDTO(restaurantOperationsCollection.searchRestaurantById(idRestaurant)));
	}

	@GetMapping
	ResponseEntity<RestaurantDTO> searchRestaurantByParameters(@RequestParam(required = false) String name,
			@RequestParam(required = false) String cuisine, @RequestParam(required = false) String cep,
			@RequestParam(required = false) Integer number) {
		return ResponseEntity.ok(restaurantMapper.restaurantToRestaurantDTO(
				restaurantOperationsCollection.searchRestaurantByParameters(name, cuisine, cep, number)));

	}

	@DeleteMapping("/{idRestaurant}")
	ResponseEntity<Void> deleteRestaurant(@PathVariable String idRestaurant) {
		restaurantOperationsCollection.deleteRestaurantById(idRestaurant);
		return ResponseEntity.noContent().build();
	}

}