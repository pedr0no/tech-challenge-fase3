package br.com.fiap.techchallenge.tablereservation.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.techchallenge.tablereservation.application.gateways.EmailGateway;
import br.com.fiap.techchallenge.tablereservation.application.gateways.ReservationGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Reservation;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ReservationUseCaseTest {

    @Mock
    private ReservationGateway reservationGateway;

    @Mock
    private EmailGateway emailGateway;

    private ReservationOperationsCollection reservationOperationsCollection;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        reservationOperationsCollection = new ReservationOperationsCollection(reservationGateway, emailGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void deveCriarReserva() {
        String reservationId = "65f252a447277444c60898ae";
        var reservation = new Reservation(
                "Restaurante Teste",
                "teste@gmail.com",
                LocalTime.of(20, 0),
                LocalDate.now().plusDays(1),
                4
        );

        reservation.setId(reservationId);
        when(reservationGateway.createReservation(reservation)).thenReturn(reservation);
        var resposta = reservationOperationsCollection.createReservation(reservation);
        assertEquals(reservation, resposta);
        verify(reservationGateway, times(1)).createReservation(reservation);
    }
}
