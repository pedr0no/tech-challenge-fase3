package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "localization")
@Getter
@Setter
public class LocalizationEntity {

	private String street;
	private Integer number;
	private String neighborhood;
	private String city;
	private String cep;
	
}
