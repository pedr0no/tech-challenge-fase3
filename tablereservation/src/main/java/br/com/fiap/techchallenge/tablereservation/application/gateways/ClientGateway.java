package br.com.fiap.techchallenge.tablereservation.application.gateways;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Client;

public interface ClientGateway {
	
	Client createClient(Client client);
	
	Client updateClient(String idClient, Client client);
	
	Client searchClientById(String idClient);
	
	void deleteClientById(String idClient);

}
