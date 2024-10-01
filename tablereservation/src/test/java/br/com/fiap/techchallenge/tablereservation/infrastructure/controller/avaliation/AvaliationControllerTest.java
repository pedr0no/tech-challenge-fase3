package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation;

import br.com.fiap.techchallenge.tablereservation.application.usecases.AvaliationOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Avaliation;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.dto.AvaliationDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.dto.CreatedAvaliationDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.mapper.AvaliationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("unit")
@WebMvcTest(AvaliationController.class)
public class AvaliationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvaliationOperationsCollection avaliationOperationsCollection;

    @MockBean
    private AvaliationMapper avaliationMapper;

    @MockBean
    private MongoTemplate mongoTemplate;

    private AvaliationDTO avaliationDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        avaliationDTO = new AvaliationDTO();
        avaliationDTO.setClientEmail("clientEmail");
        avaliationDTO.setRestaurantName("restaurantName");
        avaliationDTO.setStars(Double.valueOf(5));
        avaliationDTO.setComment("comment");
    }

    @Test
    void testCreateAvaliation() throws Exception {
        when(avaliationMapper.avaliationDTOToAvaliation(any())).thenReturn(new Avaliation());
        when(avaliationOperationsCollection.createAvaliation(any())).thenReturn(new Avaliation());
        when(avaliationMapper.avaliationToCreatedAvaliationDTO(any())).thenReturn(new CreatedAvaliationDTO());

        mockMvc.perform(post("/avaliation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"property\": \"value\"}")) // Replace with actual JSON
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testSearchAvaliationById() throws Exception {
        when(avaliationOperationsCollection.searchAvaliationById(eq("1"))).thenReturn(new Avaliation());
        when(avaliationMapper.avaliationToAvaliationDTO(any())).thenReturn(avaliationDTO);

        mockMvc.perform(get("/avaliation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.property").value("value")); // Replace with actual property
    }

    @Test
    void testSearchAvaliationsForRestaurant() throws Exception {
        when(avaliationOperationsCollection.searchAvaliationsForRestaurant(eq("restaurantName")))
                .thenReturn(Collections.singletonList(new Avaliation()));
        when(avaliationMapper.listAvaliationToListAvaliationDTO(any()))
                .thenReturn(Collections.singletonList(avaliationDTO));

        mockMvc.perform(get("/avaliation")
                        .param("restaurantName", "restaurantName"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].property").value("value")); // Replace with actual property
    }
}