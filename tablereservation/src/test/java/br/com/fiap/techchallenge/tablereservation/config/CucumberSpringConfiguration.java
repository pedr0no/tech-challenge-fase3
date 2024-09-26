package br.com.fiap.techchallenge.tablereservation.config;

import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;
import br.com.fiap.techchallenge.tablereservation.TablereservationApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = TablereservationApplication.class, properties = "spring.profiles.active=test")
public class CucumberSpringConfiguration {
}