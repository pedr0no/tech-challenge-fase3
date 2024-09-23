package br.com.fiap.techchallenge.tablereservation.steps;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.fiap.techchallenge.tablereservation.application.usecases.RestaurantOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Localization;
import br.com.fiap.techchallenge.tablereservation.domain.entity.OpeningHoursDetails;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Restaurant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class RestaurantStepDefinitions {

    @Autowired
    private RestaurantOperationsCollection restaurantOperationsCollection;

    private Restaurant restaurant;
    private String expectedRestaurantName;

    private String generateRandomRestaurantName(String baseName) {
        Random random = new Random();
        int randomNumber = random.nextInt(900) + 100;
        return baseName + " " + randomNumber;
    }

    @Given("o nome do restaurante é {string}")
    public void oNomeDoRestauranteÉ(String baseName) {
        restaurant = new Restaurant();
        expectedRestaurantName = generateRandomRestaurantName(baseName);
        restaurant.setName(expectedRestaurantName);
    }

    @Given("a localização tem a rua {string}, número {int}, bairro {string}, cidade {string}, e CEP {string}")
    public void aLocalizaçãoTemARuaENúmeroEBairroECidadeECep(String street, Integer number, String neighborhood, String city, String cep) {
        Localization localization = new Localization(street, number, neighborhood, city, cep);
        restaurant.setLocalization(localization);
    }

    @Given("o tipo de cozinha é {string}")
    public void oTipoDeCozinhaÉ(String cuisine) {
        restaurant.setCuisine(cuisine);
    }

    @Given("o restaurante tem {int} mesas, cada uma com {int} lugares")
    public void oRestauranteTemMesasCadaUmaComLugares(int quantityOfTables, int quantityPeoplePerTable) {
        restaurant.setQuantityOfTables(quantityOfTables);
        restaurant.setQuantityPeoplePerTables(quantityPeoplePerTable);
    }

    @Given("o horário de funcionamento é {string}, das {string}:{string} até {string}:{string}")
    public void o_horário_de_funcionamento_é_das_até(String day, String startHour, String startMinute, String endHour, String endMinute) {
        LocalTime openingTime = LocalTime.of(Integer.parseInt(startHour), Integer.parseInt(startMinute));
        LocalTime closingTime = LocalTime.of(Integer.parseInt(endHour), Integer.parseInt(endMinute));
        OpeningHoursDetails openingHours = new OpeningHoursDetails(LocalDate.parse(day), openingTime, closingTime, List.of());
        restaurant.setOpeningHours(List.of(openingHours));
    }

    @When("eu fizer o cadastro do restaurante")
    public void euFizerOCadastroDoRestaurante() {
        restaurant = restaurantOperationsCollection.createRestaurant(restaurant);
    }

    @Then("o sistema deve retornar o restaurante cadastrado com sucesso")
    public void oSistemaDeveRetornarORestauranteCadastradoComSucesso() {
        assertThat(restaurant.getId()).isNotNull();
        assertThat(restaurant.getName()).isEqualTo(expectedRestaurantName);
    }
//    // Deletar o restaurante após a validação de sucesso
//        restaurantOperationsCollection.deleteRestaurantById(restaurant.getId());

}
