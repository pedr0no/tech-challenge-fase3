package br.com.fiap.techchallenge.tablereservation.steps;

import br.com.fiap.techchallenge.tablereservation.application.usecases.ReservationOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Reservation;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.restassured.response.Response;
import io.cucumber.java.en.Then;
import java.time.LocalDate;
import java.time.LocalTime;


public class ReservationStepDefinitions {

    private Response response;

    @Autowired
    private ReservationOperationsCollection reservationOperationsCollection;

    private Reservation reservation;

    @Given("o nome do restaurante para reserva é {string}")
    public void oNomeDoRestauranteParaReservaÉ(String restaurantName) {
        reservation = new Reservation();
        reservation.setRestaurantName(restaurantName);
    }

    @Given("o email do cliente é {string}")
    public void oEmailDoClienteÉ(String clientEmail) {
        reservation.setClientEmail(clientEmail);
    }

    @Given("a hora da reserva é {string}")
    public void aHoraDaReservaÉ(String timeReservation) {
        reservation.setTimeReservation(LocalTime.parse(timeReservation));
    }

    @Given("o dia da reserva é {string}")
    public void oDiaDaReservaÉ(String dayOfReservation) {
        reservation.setDayOfReservation(LocalDate.parse(dayOfReservation));
    }

    @Given("a quantidade de pessoas é {int}")
    public void aQuantidadeDePessoasÉ(int quantityOfPeople) {
        reservation.setQuantityOfPeople(quantityOfPeople);
    }


    @When("eu fizer o cadastro da reserva com o email {string} e a quantidade de pessoas {int}")
    public void eu_fizer_o_cadastro_da_reserva_com_o_email_e_a_quantidade_de_pessoas(String email, Integer quantidadeDePessoas) {
        System.out.println("Cadastro de reserva com o email: " + email + " e quantidade de pessoas: " + quantidadeDePessoas);
    }


    @Then("a data da reserva é {string} realizada com sucessso")
    public void aDataDaReservaÉRealizadaComSucessso(String dataReserva) {
        System.out.println("Data da reserva: " + dataReserva);
    }
}