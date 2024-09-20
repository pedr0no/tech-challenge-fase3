package br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper;

import java.util.Objects;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Client;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity.ClientEntity;

public class ClientEntityMapper {

	public ClientEntity clientToClientEntity(Client client) {
		return new ClientEntity(client.getName(), client.getEmail());
	}

	public Client clientEntityToClient(ClientEntity entity) {
		var client = new Client();
		client.setId(entity.getId());
		client.setName(entity.getName());
		client.setEmail(entity.getEmail());
		return client;
	}

	public void outdatedClientToUpdatedClient(ClientEntity outdatedClient, ClientEntity updatedClient) {
		if (Objects.nonNull(updatedClient.getName()))
			outdatedClient.setName(updatedClient.getName());
		if (Objects.nonNull(updatedClient.getEmail()))
			outdatedClient.setEmail(updatedClient.getEmail());
	}

}
