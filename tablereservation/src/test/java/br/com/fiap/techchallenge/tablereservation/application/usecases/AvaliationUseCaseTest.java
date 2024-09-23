package br.com.fiap.techchallenge.tablereservation.application.usecases;

import br.com.fiap.techchallenge.tablereservation.application.gateways.AvaliationGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Avaliation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AvaliationUseCaseTest {

    @Mock
    private AvaliationGateway avaliationGateway;

    private AvaliationOperationsCollection avaliationOperationsCollection;

     AutoCloseable autoCloseable;
      @BeforeEach
    void setUp() {
          autoCloseable = MockitoAnnotations.openMocks(this);
          avaliationOperationsCollection = new AvaliationOperationsCollection(avaliationGateway);
      }

      @AfterEach
    void tearDown() throws Exception {
          autoCloseable.close();
      }

    @Test
    void deveCriarAvaliacao() {
        var avaliation = new Avaliation("teste@gmail.com", "Restaurante Teste", 4.5, "Excelente!");
        avaliation.setId("1");
        when(avaliationGateway.createAvaliation(avaliation)).thenReturn(avaliation);
        var resposta = avaliationOperationsCollection.createAvaliation(avaliation);
        assertEquals(avaliation, resposta);
        verify(avaliationGateway, times(1)).createAvaliation(avaliation);
    }

    @Test
    void deveListByIdRestaurante() {
        var avaliation1 = new Avaliation("joaozinho@gmail.com", "Restaurante Teste", 4.5, "Excelente!");
        avaliation1.setId("1");
        var avaliation2 = new Avaliation("maria@gmail.com", "Restaurante Teste", 5.0, "Perfeito!");
        avaliation2.setId("2");
        var restaurantName = "Restaurante Teste";
        var expectedAvaliations = List.of(avaliation1, avaliation2);
        when(avaliationGateway.searchAllAvaliationForRestaurant(restaurantName)).thenReturn(expectedAvaliations);
        var resposta = avaliationOperationsCollection.searchAvaliationsForRestaurant(restaurantName);
        assertEquals(expectedAvaliations, resposta);
        verify(avaliationGateway, times(1)).searchAllAvaliationForRestaurant(restaurantName);
    }

}
