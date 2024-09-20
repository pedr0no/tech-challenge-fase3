package br.com.fiap.techchallenge.tablereservation.infrastructure.gateway;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import br.com.fiap.techchallenge.tablereservation.application.gateways.RestaurantGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Restaurant;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper.RestaurantEntityMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.RestaurantRepository;

public class RestaurantGatewayImpl implements RestaurantGateway {

	private final RestaurantRepository restaurantRepository;
	private final RestaurantEntityMapper restaurantEntityMapper;

	public RestaurantGatewayImpl(RestaurantRepository restaurantRepository,
			RestaurantEntityMapper restaurantEntityMapper) {
		this.restaurantRepository = restaurantRepository;
		this.restaurantEntityMapper = restaurantEntityMapper;
	}

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		if (Objects.isNull(restaurantRepository.findByName(restaurant.getName()))) {
			var entity = restaurantEntityMapper.restaurantToRestaurantEntity(restaurant);
			var entitySaved = restaurantRepository.save(entity);
			return restaurantEntityMapper.entityToRestaurant(entitySaved);
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST,
				"Não foi possível salvar o Restaurante informado, pois já existe um restaurante com o mesmo nome");
	}

	@Override
	public Restaurant updateRestaurant(String idRestaurant, Restaurant restaurant) {
		var outdatedEntity = restaurantRepository.findById(idRestaurant)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
						"Não foi possível achar um Restaurante com o Id informado"));
		var updatedEntity = restaurantEntityMapper.restaurantToRestaurantEntity(restaurant);
		restaurantEntityMapper.outdatedRestaurantToUpdatedRestaurant(updatedEntity, outdatedEntity);
		return restaurantEntityMapper.entityToRestaurant(restaurantRepository.save(outdatedEntity));
	}

	@Override
	public Restaurant searchRestaurantById(String idRestaurant) {
		var entity = restaurantRepository.findById(idRestaurant)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
						"Não foi possível achar um Restaurante com o Id informado"));
		return restaurantEntityMapper.entityToRestaurant(entity);
	}

	@Override
	public Restaurant searchRestaurantByParameters(String name, String cuisine, String cep, Integer number) {
		if (Objects.nonNull(name)) {
			var entity = restaurantRepository.findByName(name);
			return restaurantEntityMapper.entityToRestaurant(entity);
		}
		if (Objects.nonNull(cuisine)) {
			var entity = restaurantRepository.findByCuisine(cuisine);
			return restaurantEntityMapper.entityToRestaurant(entity);
		}
		if (Objects.nonNull(cep)) {
			var entities = restaurantRepository.findAll();
			return restaurantEntityMapper.listEntityToRestaurant(entities, number, cep);
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "Não foi enviado parâmetro para busca de Restaurante");
	}

	@Override
	public void deleteRestaurantById(String idRestaurant) {
		restaurantRepository.findById(idRestaurant).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
				"Não foi possível achar um Restaurante com o Id informado"));
		restaurantRepository.deleteById(idRestaurant);
	}

}
