package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.fiap.techchallenge.tablereservation.application.usecases.RestaurantOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Restaurant;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.dto.RestaurantDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.restaurant.mapper.RestaurantMapper;
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

class RestaurantControllerTest {

    @Mock
    private RestaurantOperationsCollection restaurantOperationsCollection;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantController restaurantController;

    private RestaurantDTO restaurantDTO;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantDTO = new RestaurantDTO();

        restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setCuisine("Test Cuisine");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }



    @Test
    void testUpdateRestaurant() {
        when(restaurantMapper.restaurantDTOToRestaurant(any(RestaurantDTO.class))).thenReturn(restaurant);
        when(restaurantOperationsCollection.updateRestaurant(eq("1"), any(Restaurant.class))).thenReturn(null);

        ResponseEntity<Void> response = restaurantController.updateRestaurant("1", restaurantDTO);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testSearchRestaurantById() {
        when(restaurantOperationsCollection.searchRestaurantById(eq("1"))).thenReturn(restaurant);
        when(restaurantMapper.restaurantToRestaurantDTO(any())).thenReturn(restaurantDTO);

        ResponseEntity<RestaurantDTO> response = restaurantController.searchRestaurantById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(restaurantDTO, response.getBody());
    }

    @Test
    void testSearchRestaurantByParameters() {
        when(restaurantOperationsCollection.searchRestaurantByParameters(any(), any(), any(), any())).thenReturn(restaurant);
        when(restaurantMapper.restaurantToRestaurantDTO(any())).thenReturn(restaurantDTO);

        ResponseEntity<RestaurantDTO> response = restaurantController.searchRestaurantByParameters("Test Restaurant", "Test Cuisine", "12345-678", 123);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(restaurantDTO, response.getBody());
    }

    @Test
    void testDeleteRestaurant() {
        doNothing().when(restaurantOperationsCollection).deleteRestaurantById(eq("1"));

        ResponseEntity<Void> response = restaurantController.deleteRestaurant("1");

        assertEquals(204, response.getStatusCodeValue());
    }
}