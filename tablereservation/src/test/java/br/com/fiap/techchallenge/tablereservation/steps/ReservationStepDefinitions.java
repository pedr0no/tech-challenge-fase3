package br.com.fiap.techchallenge.tablereservation.steps;

import br.com.fiap.techchallenge.tablereservation.application.usecases.ReservationOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Reservation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Then;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

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
        // Implemente o código para cadastrar a reserva
        System.out.println("Cadastro de reserva com o email: " + email + " e quantidade de pessoas: " + quantidadeDePessoas);
        // Aqui você pode chamar o método que realiza o cadastro da reserva
    }

    @When("a data da reserva é {string}")
    public void a_data_da_reserva_é(String dataReserva) {
        // Implemente o código para definir a data da reserva
        System.out.println("Data da reserva: " + dataReserva);
        // Aqui você pode atribuir a data e continuar o processo de criação da reserva
    }

    @When("eu fizer o cadastro da reserva")
    public void euFizerOCadastroDaReserva() {
        // Certifique-se de que a reserva está sendo salva corretamente
        reservation = reservationOperationsCollection.createReservation(reservation);

        // Verifique se o retorno é válido
        if (reservation == null || reservation.getId() == null) {
            throw new IllegalStateException("A reserva não foi criada corretamente.");
        }
    }

    @Then("retornar status code {int}")
    public void retornarStatusCode(int statusCode) {
        // Verifique se a reserva foi criada corretamente
        if (reservation == null) {
            throw new IllegalStateException("O objeto reservation está nulo.");
        }

        given()
                .contentType("application/json")
                .body(reservation)  // Certifique-se de que 'reservation' não seja nulo
                .when()
                .post("/reservations")  // Substitua pelo seu endpoint correto
                .then()
                .statusCode(statusCode);
    }
}

