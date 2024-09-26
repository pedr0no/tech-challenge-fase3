package br.com.fiap.techchallenge.tablereservation.application.usecases;

import br.com.fiap.techchallenge.tablereservation.application.gateways.ClientGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Client;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ClientUseCaseTest {

    @Mock
    private ClientGateway clientGateway;

    private ClientOperationsCollection clientOperationsCollection;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        clientOperationsCollection = new ClientOperationsCollection(clientGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void deveCriarCliente() {
        var client = new Client("Sérgio", "sergio@gmail.com");
        client.setId("1");

        when(clientGateway.createClient(client)).thenReturn(client);

        var resposta = clientOperationsCollection.createClient(client);

        assertEquals(client, resposta);
        verify(clientGateway, times(1)).createClient(client);
    }

    @Test
    void deveListarPorFiltro() {
        var client1 = new Client("Sérgio", "sergio@gmail.com");
        client1.setId("1");

        var client2 = new Client("Maria", "maria@gmail.com");
        client2.setId("2");

        var expectedClients = List.of(client1, client2);
        var filter = "Sérgio";

        // Supondo que a implementação do ClientGateway tenha um método de busca por filtro
        when(clientGateway.searchClientById("1")).thenReturn(client1);

        var resposta = clientOperationsCollection.searchClientById("1");

        assertEquals(client1, resposta);
        verify(clientGateway, times(1)).searchClientById("1");
    }

    @Test
    void deveEncontrarPorId() {
        var client = new Client("Sérgio", "sergio@gmail.com");
        client.setId("1");

        when(clientGateway.searchClientById("1")).thenReturn(client);

        var resposta = clientOperationsCollection.searchClientById("1");

        assertEquals(client, resposta);
        verify(clientGateway, times(1)).searchClientById("1");
    }

    @Test
    void deveLancarErroAoCriarClienteSemCamposObrigatorios() {
        var clientInvalido = new Client();
        clientInvalido.setName(null); // ou um string vazia
        clientInvalido.setEmail(null); // ou um string vazia

        var exception = assertThrows(BusinessException.class, () -> {
            clientOperationsCollection.createClient(clientInvalido);
        });

        assertEquals("Há campos obrigatórios que não foram enviados name, email,", exception.getMessage());
    }
}
