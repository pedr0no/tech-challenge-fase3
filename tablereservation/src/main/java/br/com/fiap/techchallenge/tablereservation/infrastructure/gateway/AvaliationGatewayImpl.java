package br.com.fiap.techchallenge.tablereservation.infrastructure.gateway;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.techchallenge.tablereservation.application.gateways.AvaliationGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Avaliation;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper.AvaliationEntityMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.AvaliationRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.ClientRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.RestaurantRepository;

public class AvaliationGatewayImpl implements AvaliationGateway {

	private final AvaliationRepository avaliationRepository;
	private final RestaurantRepository restaurantRepository;
	private final ClientRepository clientRepository;
	private final AvaliationEntityMapper avaliationEntityMapper;

	public AvaliationGatewayImpl(AvaliationRepository avaliationRepository, RestaurantRepository restaurantRepository,
			AvaliationEntityMapper avaliationEntityMapper, ClientRepository clientRepository) {
		this.avaliationRepository = avaliationRepository;
		this.restaurantRepository = restaurantRepository;
		this.clientRepository = clientRepository;
		this.avaliationEntityMapper = avaliationEntityMapper;
	}

	@Override
	@Transactional
	public Avaliation createAvaliation(Avaliation avaliation) {
		var restaurant = restaurantRepository.findByName(avaliation.getRestaurantName());
		var client = clientRepository.findByEmail(avaliation.getClientEmail());
		var entity = avaliationEntityMapper.buildAvaliationEntity(client, restaurant, avaliation);
		restaurant.getAvaliations().add(entity);
		Double evaluationAverage = avaliationEntityMapper.calculateEvaluationAverage(restaurant, avaliation.getStars());
		restaurant.setAvaliationAverage(evaluationAverage);
		var entitySaved = avaliationRepository.save(entity);
		restaurantRepository.save(restaurant);
		return avaliationEntityMapper.avaliationEntityToAvaliation(entitySaved);
	}

	@Override
	public Avaliation searchAvaliationById(String idAvaliation) {
		return avaliationEntityMapper.avaliationEntityToAvaliation(avaliationRepository.findById(idAvaliation)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
						"Não foi possível achar uma Avaliação com o Id informado")));
	}

	@Override
	public List<Avaliation> searchAllAvaliationForRestaurant(String restaurantName) {
		return avaliationEntityMapper.listEntityToListAvaliation(restaurantRepository.findByName(restaurantName));
	}

}