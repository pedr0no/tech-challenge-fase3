package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "client")
@Getter
@Setter
public class ClientEntity {
	
	@Id
	private String id;
	private String name;
	private String email;
	
	public ClientEntity(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
}
