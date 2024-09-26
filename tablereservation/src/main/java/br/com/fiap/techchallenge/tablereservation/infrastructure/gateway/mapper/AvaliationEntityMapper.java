package br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Avaliation;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.AvaliationEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.ClientEntity;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.techchallenge.tablereservation.main.Utils;

public class AvaliationEntityMapper {

	// Métodos que mapeiam de Domain para Entity

	public AvaliationEntity buildAvaliationEntity(ClientEntity clientEntity, RestaurantEntity restaurantEntity,
			Avaliation avaliation) {
		var entity = new AvaliationEntity();
		entity.setClient(clientEntity);
		entity.setRestaurant(restaurantEntity);
		entity.setStars(avaliation.getStars());
		entity.setComment(avaliation.getComment());
		return entity;
	}

	public Double calculateEvaluationAverage(RestaurantEntity restaurantEntity, double stars) {
		double sum = stars + restaurantEntity.getAvaliationAverage();
		return sum / restaurantEntity.getAvaliations().size();
	}
	
	// Métodos que mapeiam de Entity para Domain

	public Avaliation avaliationEntityToAvaliation(AvaliationEntity avaliationEntity) {
		return new Avaliation(avaliationEntity.getId(), avaliationEntity.getClient().getEmail(),
				avaliationEntity.getRestaurant().getName(), avaliationEntity.getStars(), avaliationEntity.getComment());
	}
	
	public List<Avaliation> listEntityToListAvaliation(RestaurantEntity restaurantEntity) {
		if(Utils.isValidList(restaurantEntity.getAvaliations())) {
			var list = new ArrayList<Avaliation>();
			restaurantEntity.getAvaliations().forEach(a-> {
				var avaliation = avaliationEntityToAvaliation(a);
				list.add(avaliation);
			});
			return list;
		}
		throw new BusinessException(HttpStatus.NOT_FOUND, "O restaurante não possui avaliações no momento");
	}
}