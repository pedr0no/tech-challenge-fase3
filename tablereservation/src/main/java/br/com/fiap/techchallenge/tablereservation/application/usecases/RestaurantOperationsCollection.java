package br.com.fiap.techchallenge.tablereservation.application.usecases;

import br.com.fiap.techchallenge.tablereservation.application.gateways.RestaurantGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Restaurant;
import java.util.List;

public class RestaurantOperationsCollection {

	private final RestaurantGateway restaurantGateway;

	public RestaurantOperationsCollection(RestaurantGateway restaurantOperations) {
		this.restaurantGateway = restaurantOperations;
	}

	public Restaurant createRestaurant(Restaurant restaurant) {
		return restaurantGateway.createRestaurant(restaurant);
	}

	public Restaurant updateRestaurant(String idRestaurant, Restaurant restaurant) {
		return restaurantGateway.updateRestaurant(idRestaurant, restaurant);
	}

	public Restaurant searchRestaurantById(String idRestaurant) {
		return restaurantGateway.searchRestaurantById(idRestaurant);
	}

	public Restaurant searchRestaurantByParameters(String name, String cuisine, String cep, Integer number) {
		return restaurantGateway.searchRestaurantByParameters(name, cuisine, cep, number);
	}

	public void deleteRestaurantById(String idRestaurant) {
		restaurantGateway.deleteRestaurantById(idRestaurant);
	}

	public List<Restaurant> listByFilter(String city) {
		return restaurantGateway.listByFilter(city);  // Chamando o método da interface
	}

}
