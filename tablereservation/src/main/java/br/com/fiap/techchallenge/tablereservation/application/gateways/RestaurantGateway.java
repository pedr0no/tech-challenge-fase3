package br.com.fiap.techchallenge.tablereservation.application.gateways;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Restaurant;

public interface RestaurantGateway {
	
	Restaurant createRestaurant(Restaurant restaurant);
	
	Restaurant updateRestaurant(String idRestaurant, Restaurant restaurant);
	
	Restaurant searchRestaurantById(String idRestaurant);
	
	Restaurant searchRestaurantByParameters(String name, String cuisine, String cep, Integer number);

	void deleteRestaurantById(String idRestaurant);
}
