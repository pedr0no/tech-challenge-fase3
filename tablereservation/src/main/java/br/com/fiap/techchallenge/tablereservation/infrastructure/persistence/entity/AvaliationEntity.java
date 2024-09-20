package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "avaliation")
@Getter
@Setter
public class AvaliationEntity {
	
	@Id
	private String id;
	@DBRef
	private ClientEntity client;
	@DBRef
	private RestaurantEntity restaurant;
	private Double stars;
	private String comment;
	
}