package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.RestaurantEntity;

@Repository
public interface RestaurantRepository extends MongoRepository<RestaurantEntity, String> {
	
	RestaurantEntity findByName(String name);
	
	RestaurantEntity findByCuisine(String cuisine);
	
}