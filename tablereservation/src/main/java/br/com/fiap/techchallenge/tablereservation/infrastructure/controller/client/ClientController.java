package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.techchallenge.tablereservation.application.usecases.ClientOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.dto.ClientDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.mapper.ClientMapper;
import br.com.fiap.techchallenge.tablereservation.main.Utils;

@RestController
@RequestMapping("/client")
public class ClientController {

	private final ClientOperationsCollection clientOperationsCollection;
	private final ClientMapper clientMapper;

	public ClientController(ClientOperationsCollection clientOperationsCollection, ClientMapper clientMapper) {
		this.clientOperationsCollection = clientOperationsCollection;
		this.clientMapper = clientMapper;
	}

	@PostMapping
	ResponseEntity<Void> createClient(@RequestBody ClientDTO body) {
		var client = clientMapper.createClientDTOToClient(body);
		return ResponseEntity
				.created(Utils.getLocation("/client")).headers(clientMapper
						.clientToCreatedClientDTO(clientOperationsCollection.createClient(client)).getHttpHeader())
				.build();
	}

	@GetMapping("/{idClient}")
	ResponseEntity<ClientDTO> searchClientById(@PathVariable String idClient) {
		return ResponseEntity
				.ok(clientMapper.clientToSearchClientDTO(clientOperationsCollection.searchClientById(idClient)));
	}
	
	@PutMapping("/{idClient}")
	ResponseEntity<Void> updateClient(@PathVariable String idClient, @RequestBody ClientDTO body) {
		clientOperationsCollection.updateClient(idClient, clientMapper.updateClientDTOToClient(body));
		return ResponseEntity.noContent().build();
	}


	@DeleteMapping("/{idClient}")
	ResponseEntity<Void> deleteClient(@PathVariable String idClient) {
		clientOperationsCollection.deleteClientById(idClient);
		return ResponseEntity.noContent().build();
	}

}
