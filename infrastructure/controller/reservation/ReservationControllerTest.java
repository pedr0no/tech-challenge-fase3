package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation;

import static org.junit.jupiter.api.Assertions.*;

import br.com.fiap.techchallenge.tablereservation.application.usecases.ReservationOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Reservation;
import br.com.fiap.techchallenge.tablereservation.domain.enums.ReservationStatus;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.dto.ReservationDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.reservation.mapper.ReservationMapper;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ReservationControllerTest {

    @Mock
    private ReservationOperationsCollection reservationOperationsCollection;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationController reservationController;

    private ReservationDTO reservationDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testSearchReservationById() {
        var reservation = new Reservation();

        when(reservationOperationsCollection.searchReservationById(eq("1"))).thenReturn(reservation);
        when(reservationMapper.reservationToReservationDTO(any())).thenReturn(reservationDTO);

        ResponseEntity<ReservationDTO> response = reservationController.searchReservationById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(reservationDTO, response.getBody());
    }

    @Test
    void testSearchReservationsByDate() {
        var date = LocalDate.now();
        var restaurantName = "Test Restaurant";

        when(reservationOperationsCollection.searchReservationByDate(eq(date), eq(restaurantName)))
                .thenReturn(Collections.singletonList(new Reservation()));
        when(reservationMapper.listReservationsToListReservationDTO(any()))
                .thenReturn(Collections.singletonList(reservationDTO));

        ResponseEntity<List<ReservationDTO>> response = reservationController.searchReservationsByDate(date, restaurantName);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(reservationDTO, response.getBody().get(0));
    }

    @Test
    void testUpdateReservationStatus() {
        doNothing().when(reservationOperationsCollection).updateReservationStatus(eq("1"), any());

        ResponseEntity<Void> response = reservationController.updateReservationStatus("1", ReservationStatus.CONFIRMED);

        assertEquals(204, response.getStatusCodeValue());
    }
}