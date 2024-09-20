package br.com.fiap.techchallenge.tablereservation.infrastructure.gateway;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import br.com.fiap.techchallenge.tablereservation.application.gateways.ClientGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Client;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import br.com.fiap.techchallenge.tablereservation.infrastructure.gateway.mapper.ClientEntityMapper;
import br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.ClientRepository;

public class ClientGatewayImpl implements ClientGateway {

	private final ClientRepository clientRepository;
	private final ClientEntityMapper clientEntityMapper;

	public ClientGatewayImpl(ClientRepository clientRepository, ClientEntityMapper clientEntityMapper) {
		this.clientRepository = clientRepository;
		this.clientEntityMapper = clientEntityMapper;
	}

	@Override
	public Client createClient(Client client) {
		if (Objects.isNull(clientRepository.findByEmail(client.getEmail()))) {
			var entity = clientEntityMapper.clientToClientEntity(client);
			return clientEntityMapper.clientEntityToClient(clientRepository.save(entity));
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST,
				"Não foi possível salvar o Cliente informado, pois já existe um cliente com o mesmo e-mail");
	}

	@Override
	public Client updateClient(String idClient, Client client) {
		var outdatedClient = clientRepository.findById(idClient)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
						"Não foi possível encontrar um Cliente com o id informado"));
		var updatedEntity = clientEntityMapper.clientToClientEntity(client);
		clientEntityMapper.outdatedClientToUpdatedClient(outdatedClient, updatedEntity);
		return clientEntityMapper.clientEntityToClient(clientRepository.save(outdatedClient));
	}

	@Override
	public Client searchClientById(String idClient) {
		var entity = clientRepository.findById(idClient).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
				"Não foi possível encontrar um Cliente com o id informado"));
		return clientEntityMapper.clientEntityToClient(entity);
	}

	@Override
	public void deleteClientById(String idClient) {
		clientRepository.findById(idClient).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
				"Não foi possível encontrar um Cliente com o id informado"));
		clientRepository.deleteById(idClient);
	}

}
