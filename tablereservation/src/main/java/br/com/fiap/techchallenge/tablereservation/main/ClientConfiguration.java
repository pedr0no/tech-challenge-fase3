package br.com.fiap.techchallenge.tablereservation.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.techchallenge.tablereservation.application.gateways.ClientGateway;
import br.com.fiap.techchallenge.tablereservation.application.usecases.ClientOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.mapper.ClientMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.ClientGatewayImpl;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper.ClientEntityMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.ClientRepository;

@Configuration
public class ClientConfiguration {
	
	@Bean
	ClientOperationsCollection createClientOperationsCollection(ClientGateway clientGateway) {
		return new ClientOperationsCollection(clientGateway);
	}
	
	@Bean
	ClientGateway createClientGateway(ClientRepository clientRepository, ClientEntityMapper clientEntityMapper) {
		return new ClientGatewayImpl(clientRepository, clientEntityMapper);
	}
	
	@Bean
	ClientEntityMapper createClientEntityMapper() {
		return new ClientEntityMapper();
	}
	
	@Bean
	ClientMapper createClientMapper() {
		return new ClientMapper();
	}

}
