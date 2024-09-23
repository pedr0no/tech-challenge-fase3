package br.com.fiap.techchallenge.tablereservation.steps;

import br.com.fiap.techchallenge.tablereservation.application.usecases.ClientOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Client;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@AutoConfigureDataMongo
public class ClientStepDefinitions {

    @Autowired
    private ClientOperationsCollection clientOperationsCollection;

    private Client client;

    private String generatedName;
    private String generatedEmail;

    // Método para gerar um número aleatório de 3 dígitos
    private String generateRandomDigits() {
        Random random = new Random();
        int randomDigits = 100 + random.nextInt(900);  // Gera número de 100 a 999
        return String.valueOf(randomDigits);
    }

    @Given("o nome do cliente é gerado aleatoriamente")
    public void o_nome_do_cliente_é_gerado_aleatoriamente() {
        generatedName = "testado" + generateRandomDigits();
        client = new Client();
        client.setName(generatedName);
    }

    @Given("o email do cliente é gerado aleatoriamente")
    public void o_email_do_cliente_é_gerado_aleatoriamente() {
        generatedEmail = "testador" + generateRandomDigits() + "@gmail.com";
        client.setEmail(generatedEmail);
    }

    @When("eu fizer o cadastro do cliente")
    public void eu_fizer_o_cadastro_do_cliente() {
        client = clientOperationsCollection.createClient(client);
    }

    @Then("o sistema deve retornar o cliente cadastrado com sucesso")
    public void o_sistema_deve_retornar_o_cliente_cadastrado_com_sucesso() {
        assertThat(client.getId()).isNotNull();
        assertThat(client.getName()).isEqualTo(generatedName);  // Valida o nome gerado
        assertThat(client.getEmail()).isEqualTo(generatedEmail);  // Valida o email gerado
    }
}