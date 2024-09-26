package br.com.fiap.techchallenge.tablereservation.bdd;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.test.context.ActiveProfiles;


//@Suite
//@SelectClasspathResource("features/restaurant.feature")
//@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-report.json")
//@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "br.com.fiap.techchallenge.tablereservation.steps,br.com.fiap.techchallenge.tablereservation.bdd.config")
//@ActiveProfiles("reservation-test")
//public class CucumberTest {
//}
@Suite
@SelectClasspathResource("features/restaurant.feature")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-report.json")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "br.com.fiap.techchallenge.tablereservation.steps,br.com.fiap.techchallenge.tablereservation.bdd.config")
@ActiveProfiles("test") // Consistência no perfil de teste
public class CucumberTest {
}