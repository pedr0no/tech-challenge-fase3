package br.com.fiap.techchallenge.tablereservation.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.techchallenge.tablereservation.application.gateways.AvaliationGateway;
import br.com.fiap.techchallenge.tablereservation.application.usecases.AvaliationOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.mapper.AvaliationMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.AvaliationGatewayImpl;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper.AvaliationEntityMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.AvaliationRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.ClientRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.RestaurantRepository;

@Configuration
public class AvaliationConfiguration {

	@Bean
	AvaliationOperationsCollection createAvaliationOperationsCollection(AvaliationGateway avaliationGateway) {
		return new AvaliationOperationsCollection(avaliationGateway);
	}

	@Bean
	AvaliationGateway createAvaliationGateway(AvaliationRepository avaliationRepository,
			RestaurantRepository restaurantRepository, AvaliationEntityMapper avaliationEntityMapper,
			ClientRepository clientRepository) {
		return new AvaliationGatewayImpl(avaliationRepository, restaurantRepository, avaliationEntityMapper,
				clientRepository);
	}

	@Bean
	AvaliationEntityMapper createAvaliationEntityMapper() {
		return new AvaliationEntityMapper();
	}

	@Bean
	AvaliationMapper createAvaliationMapper() {
		return new AvaliationMapper();
	}
}
