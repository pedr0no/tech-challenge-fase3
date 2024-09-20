package br.com.fiap.techchallenge.tablereservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.AvaliationRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.ClientRepository;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.RestaurantRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {
		RestaurantRepository.class,
		ClientRepository.class,
		RestaurantRepository.class,
		AvaliationRepository.class
})
public class TablereservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TablereservationApplication.class, args);
	}

}
