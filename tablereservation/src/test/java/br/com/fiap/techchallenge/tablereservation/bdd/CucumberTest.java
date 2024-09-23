package br.com.fiap.techchallenge.tablereservation.bdd;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.test.context.ActiveProfiles;

@Suite
@SelectClasspathResource("features/restaurant_operations.feature")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-report.json")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "br.com.fiap.techchallenge.tablereservation.bdd.steps")
@ActiveProfiles("reservation-test")  // Aplicando o perfil para os testes de reserva
public class CucumberTest {
}