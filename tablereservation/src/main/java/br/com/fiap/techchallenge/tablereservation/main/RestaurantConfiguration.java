package br.com.fiap.techchallenge.tablereservation.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.techchallenge.tablereservation.application.gateways.RestaurantGateway;
import br.com.fiap.techchallenge.tablereservation.application.usecases.RestaurantOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.mapper.RestaurantMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.RestaurantGatewayImpl;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper.RestaurantEntityMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.RestaurantRepository;

@Configuration
public class RestaurantConfiguration {

	@Bean
	RestaurantOperationsCollection createRestaurantOperationsCollection(RestaurantGateway restaurantGateway) {
		return new RestaurantOperationsCollection(restaurantGateway);
	}

	@Bean
	RestaurantGateway createRestaurantGateway(RestaurantRepository restaurantRepository,
			RestaurantEntityMapper restaurantEntityMapper) {
		return new RestaurantGatewayImpl(restaurantRepository, restaurantEntityMapper);
	}

	@Bean
	RestaurantEntityMapper createRestaurantEntityMapper() {
		return new RestaurantEntityMapper();
	}

	@Bean
	RestaurantMapper createRestaurantMapper() {
		return new RestaurantMapper();
	}

}
