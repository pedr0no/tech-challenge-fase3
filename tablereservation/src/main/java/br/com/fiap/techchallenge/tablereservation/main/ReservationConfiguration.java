package br.com.fiap.techchallenge.tablereservation.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.fiap.techchallenge.tablereservation.application.gateways.EmailGateway;
import br.com.fiap.techchallenge.tablereservation.application.gateways.ReservationGateway;
import br.com.fiap.techchallenge.tablereservation.application.usecases.ReservationOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.mapper.ReservationMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.EmailGatewayImpl;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.ReservationGatewayImpl;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper.ReservationEntityMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.ClientRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.ReservationRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.RestaurantRepository;

@Configuration
public class ReservationConfiguration {

	@Bean
	ReservationOperationsCollection createReservationOperationsCollection(ReservationGateway reservationGateway,
			EmailGateway emailGateway) {
		return new ReservationOperationsCollection(reservationGateway, emailGateway);
	}

	@Bean
	ReservationGateway createReservationGateway(RestaurantRepository restaurantRepository,
			ClientRepository clientRepository, ReservationEntityMapper reservationEntityMapper,
			ReservationRepository reservationRepository) {
		return new ReservationGatewayImpl(restaurantRepository, clientRepository, reservationEntityMapper,
				reservationRepository);
	}

	@Bean
	EmailGateway createEmailGateway(JavaMailSender mailSender) {
		return new EmailGatewayImpl(mailSender);
	}

	@Bean
	ReservationEntityMapper createReservationEntityMapper() {
		return new ReservationEntityMapper();
	}

	@Bean
	ReservationMapper createReservationMapper() {
		return new ReservationMapper();
	}

}
