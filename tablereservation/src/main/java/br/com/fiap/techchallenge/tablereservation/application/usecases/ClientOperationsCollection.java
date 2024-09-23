package br.com.fiap.techchallenge.tablereservation.application.usecases;

import org.springframework.http.HttpStatus;

import br.com.fiap.techchallenge.tablereservation.application.gateways.ClientGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Client;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import br.com.fiap.techchallenge.tablereservation.main.Utils;

public class ClientOperationsCollection {

	private final ClientGateway clientGateway;

	public ClientOperationsCollection(ClientGateway clientGateway) {
		this.clientGateway = clientGateway;
	}

	public Client createClient(Client client) {
		validateClientFields(client);
		return clientGateway.createClient(client);
	}

	private void validateCLientFields(Client client) {
	}

	public Client updateClient(String idClient, Client client) {
		return clientGateway.updateClient(idClient, client);
	}

	public Client searchClientById(String idClient) {
		return clientGateway.searchClientById(idClient);
	}

	public void deleteClientById(String idClient) {
		clientGateway.deleteClientById(idClient);
	}

	private void validateClientFields(Client client) {
		var stringBuilder = new StringBuilder();
		if (!Utils.isValidString(client.getName())) // Verifica se o nome é inválido
			stringBuilder.append(" name,");
		if (!Utils.isValidString(client.getEmail())) // Verifica se o email é inválido
			stringBuilder.append(" email,");
		if (!stringBuilder.isEmpty())
			throw new BusinessException(HttpStatus.BAD_REQUEST,
					"Há campos obrigatórios que não foram enviados" + stringBuilder.toString());
	}

}
