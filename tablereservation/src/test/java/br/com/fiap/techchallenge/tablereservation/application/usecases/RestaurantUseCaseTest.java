package br.com.fiap.techchallenge.tablereservation.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.techchallenge.tablereservation.application.gateways.RestaurantGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Restaurant;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Localization;
import br.com.fiap.techchallenge.tablereservation.domain.entity.OpeningHoursDetails;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class RestaurantUseCaseTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    private RestaurantOperationsCollection restaurantOperationsCollection;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {
          autoCloseable = MockitoAnnotations.openMocks(this);
        restaurantOperationsCollection = new RestaurantOperationsCollection(restaurantGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void deveCriarRestaurante() {
        var localization = new Localization("Rua Exemplo", "12345-678", "São Paulo", "SP", 10);
        var openingHours = List.of(new OpeningHoursDetails("Segunda-feira", "08:00", "22:00"));
        var restaurant = new Restaurant(
                "Restaurante Teste",
                localization,
                "Italiana",
                4,
                10,
                openingHours
        );
        restaurant.setId("12345");

        when(restaurantGateway.createRestaurant(restaurant)).thenReturn(restaurant);
        var resposta = restaurantOperationsCollection.createRestaurant(restaurant);
        assertEquals(restaurant, resposta);
        verify(restaurantGateway, times(1)).createRestaurant(restaurant);
    }

    @Test
    void deveListarPorFiltro() {
        var localization = new Localization("Rua Exemplo", "12345-678", "São Paulo", "SP", 10);
        var openingHours = List.of(new OpeningHoursDetails("Segunda-feira", "08:00", "22:00"));
        var restaurant = new Restaurant("Restaurante Teste", localization, "Italiana", 4, 10, openingHours);
        List<Restaurant> expectedRestaurants = List.of(restaurant);
        when(restaurantGateway.listByFilter("São Paulo")).thenReturn(expectedRestaurants);
        var resposta = restaurantOperationsCollection.listByFilter("São Paulo");
        assertEquals(expectedRestaurants, resposta);
        verify(restaurantGateway, times(1)).listByFilter("São Paulo");
    }

    @Test
    void deveRetornarListaVaziaAoListarPorFiltroInexistente() {
        when(restaurantGateway.listByFilter("Filtro Inexistente")).thenReturn(List.of());
        var resposta = restaurantOperationsCollection.listByFilter("Filtro Inexistente");
        assertEquals(List.of(), resposta);
        verify(restaurantGateway, times(1)).listByFilter("Filtro Inexistente");
    }

}
