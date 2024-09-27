package br.com.fiap.techchallenge.tablereservation.Performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import org.junit.jupiter.api.Tag;

import java.time.Duration;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

@Tag("performance")
public class ApiPerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")  // Base URL da API
            .header("Content-Type", "application/json");

    ActionBuilder criarClienteRequest = http("criar cliente")
            .post("/tableReservation/api/client")
            .body(StringBody(session -> {
                String nomeCliente = "ClienteTest" + System.currentTimeMillis();
                return "{\n" +
                        "    \"name\": \"" + nomeCliente + "\",\n" +
                        "    \"email\": \"tomas.amig" + System.currentTimeMillis() + "@hotmail.com\"\n" +
                        "}";
            }))
            .check(status().is(201));

    ScenarioBuilder cenarioCriarCliente = scenario("Criar Cliente")
            .exec(criarClienteRequest);

    ActionBuilder criarReservaRequest = http("criar reserva")
            .post("/tableReservation/api/reservation")
            .body(StringBody("{\n" +
                    "    \"restaurantName\": \"Taco Bell\",\n" +
                    "    \"clientEmail\": \"daniela.ono@hotmail.com\",\n" +
                    "    \"timeReservation\": \"15:00\",\n" +
                    "    \"dayOfReservation\": \"2024-09-28\",\n" +
                    "    \"quantityOfPeople\": 4\n" +
                    "}"))
            .check(status().is(201));

    ScenarioBuilder cenarioCriarReserva = scenario("Criar Reserva")
            .exec(criarReservaRequest);

    ActionBuilder adicionarRestauranteRequest = http("adicionar restaurante")
            .post("/tableReservation/api/restaurant")
            .body(StringBody(session -> {
                String nomeRestaurante = "RestauranteTest" + System.currentTimeMillis();
                return "{\n" +
                        "  \"name\": \"" + nomeRestaurante + "\",\n" +
                        "  \"cuisine\": \"ITALIANA\",\n" +
                        "  \"quantityPeoplePerTables\": 6,\n" +
                        "  \"quantityOfTables\": 20,\n" +
                        "  \"localization\": {\n" +
                        "    \"street\": \"Avenida Paulista\",\n" +
                        "    \"number\": 1245,\n" +
                        "    \"neighborhood\": \"Consolação\",\n" +
                        "    \"city\": \"São Paulo\",\n" +
                        "    \"cep\": \"01310-930\"\n" +
                        "  },\n" +
                        "  \"openingHours\": [\n" +
                        "    {\n" +
                        "      \"day\": \"2024-09-28\",\n" +
                        "      \"openingTime\": \"12:00\",\n" +
                        "      \"closingTime\": \"22:00\",\n" +
                        "      \"scheduleOfReservations\": [\n" +
                        "        {\"hour\": \"12:00\"},\n" +
                        "        {\"hour\": \"13:00\"},\n" +
                        "        {\"hour\": \"14:00\"},\n" +
                        "        {\"hour\": \"15:00\"},\n" +
                        "        {\"hour\": \"16:00\"}\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";
            }))
            .check(status().is(201));

    ScenarioBuilder cenarioCadastrarRestaurante = scenario("Cadastrar Restaurante")
            .exec(adicionarRestauranteRequest);

    ActionBuilder listarRestaurantesRequest = http("listar restaurantes")
            .get("/tableReservation/api/restaurant")
            .queryParam("cep", "01310-930")
            .queryParam("number", "1245")
            .check(status().is(200));

    ScenarioBuilder cenarioListarRestaurantes = scenario("Listar Restaurantes")
            .exec(listarRestaurantesRequest);

    {
        setUp(
                cenarioCriarCliente.injectOpen(
                        rampUsersPerSec(1).to(5).during(Duration.ofSeconds(3)),
                        constantUsersPerSec(5).during(Duration.ofSeconds(3)),
                        rampUsersPerSec(5).to(1).during(Duration.ofSeconds(2))
                ),

                cenarioCriarReserva.injectOpen(atOnceUsers(1)),
                cenarioCadastrarRestaurante.injectOpen(
                        rampUsersPerSec(1).to(5).during(Duration.ofSeconds(3)),
                        constantUsersPerSec(5).during(Duration.ofSeconds(3)),
                        rampUsersPerSec(5).to(1).during(Duration.ofSeconds(2))
                ),
                cenarioListarRestaurantes.injectOpen(
                        rampUsersPerSec(1).to(5).during(Duration.ofSeconds(3)),
                        constantUsersPerSec(5).during(Duration.ofSeconds(3)),
                        rampUsersPerSec(5).to(1).during(Duration.ofSeconds(2))
                )
        )
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(20000),
                        global().failedRequests().count().is(0L)

                );
    }
}
