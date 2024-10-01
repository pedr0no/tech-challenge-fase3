package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client;

import static org.junit.jupiter.api.Assertions.*;

import br.com.fiap.techchallenge.tablereservation.application.usecases.ClientOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Client;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.dto.ClientDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.dto.CreatedClientDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.client.mapper.ClientMapper;
import br.com.fiap.techchallenge.tablereservation.main.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @Mock
    private ClientOperationsCollection clientOperationsCollection;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientController clientController;

    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientDTO = new ClientDTO();
        // Configure ClientDTO properties here
    }

    @Test
    void testCreateClient() {
        var client = new Client();
        var createdClientDTO = mock(CreatedClientDTO.class); // Mock CreatedClientDTO
        var headers = new HttpHeaders();
        headers.setLocation(UriComponentsBuilder.fromPath("/client").build().toUri());

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(clientMapper.createClientDTOToClient(any())).thenReturn(client);
        when(clientOperationsCollection.createClient(any())).thenReturn(client);
        when(clientMapper.clientToCreatedClientDTO(any())).thenReturn(createdClientDTO);
        when(createdClientDTO.getHttpHeader()).thenReturn(headers);

        ResponseEntity<Void> response = clientController.createClient(clientDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("/client", response.getHeaders().getLocation().getPath());
    }

    @Test
    void testSearchClientById() {
        var client = new Client();

        when(clientOperationsCollection.searchClientById(eq("1"))).thenReturn(client);
        when(clientMapper.clientToSearchClientDTO(any())).thenReturn(clientDTO);

        ResponseEntity<ClientDTO> response = clientController.searchClientById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clientDTO, response.getBody());
    }

    @Test
    void testUpdateClient() {
        doAnswer(invocation -> {
            // Simulate the updateClient method
            return null;
        }).when(clientOperationsCollection).updateClient(eq("1"), any());

        ResponseEntity<Void> response = clientController.updateClient("1", clientDTO);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteClient() {
        doNothing().when(clientOperationsCollection).deleteClientById(eq("1"));

        ResponseEntity<Void> response = clientController.deleteClient("1");

        assertEquals(204, response.getStatusCodeValue());
    }
}