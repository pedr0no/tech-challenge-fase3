package br.com.fiap.techchallenge.tablereservation.steps;


import br.com.fiap.techchallenge.tablereservation.application.usecases.AvaliationOperationsCollection;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Avaliation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.junit.jupiter.api.Tag;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Tag("integration")
public class AvaliationStepMockDefinitions {
    @Mock
    private AvaliationOperationsCollection avaliationOperationsCollection;

    @InjectMocks
    private Avaliation avaliation;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializando os mocks
    }

    @Given("o cliente com o email {string} avalia o restaurante {string}")
    public void oClienteAvaliaORestaurante(String email, String restaurantName) {
        avaliation = new Avaliation(email, restaurantName, 4.5, "Comida excelente!");
        avaliation.setId("1");
    }

    @Given("a nota da avaliação é {double}")
    public void aNotaDaAvaliacaoÉ(Double stars) {
        avaliation.setStars(stars);
    }

    @Given("o comentário da avaliação é {string}")
    public void oComentarioDaAvaliacaoÉ(String comment) {
        avaliation.setComment(comment);
    }

    @When("a avaliação for criada")
    public void aAvaliacaoForCriada() {
        when(avaliationOperationsCollection.createAvaliation(avaliation)).thenReturn(avaliation);
    }

    @Then("a avaliação deve ser criada com sucesso")
    public void aAvaliacaoDeveSerCriadaComSucesso() {
        var resposta = avaliationOperationsCollection.createAvaliation(avaliation);
        assertEquals(avaliation, resposta);
        verify(avaliationOperationsCollection, times(1)).createAvaliation(avaliation);
    }
}