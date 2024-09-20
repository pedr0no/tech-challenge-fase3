package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.mapper;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Client;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.dto.ClientDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.dto.CreatedClientDTO;

public class ClientMapper {

	public Client createClientDTOToClient(ClientDTO clientDTO) {
		return new Client(clientDTO.getName(), clientDTO.getEmail());
	}

	public CreatedClientDTO clientToCreatedClientDTO(Client client) {
		return new CreatedClientDTO(client.getId());
	}

	public Client updateClientDTOToClient(ClientDTO clientDTO) {
		return new Client(clientDTO.getName(), clientDTO.getEmail());
	}

	public ClientDTO clientToSearchClientDTO(Client client) {
		return new ClientDTO(client.getName(), client.getEmail());
	}

}
