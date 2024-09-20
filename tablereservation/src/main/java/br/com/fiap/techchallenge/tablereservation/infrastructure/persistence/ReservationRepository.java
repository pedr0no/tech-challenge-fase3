package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.ReservationEntity;

@Repository
public interface ReservationRepository extends MongoRepository<ReservationEntity, String> {
	
}