package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.ClientEntity;

@Repository
public interface ClientRepository extends MongoRepository<ClientEntity, String> {

	ClientEntity findByEmail(String email);
	
}
