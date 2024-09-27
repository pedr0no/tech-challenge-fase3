@Tag("integration")
Feature: Cadastro de restaurante

  Scenario: Cadastrar um novo restaurante

    Given o nome do restaurante é "Restaurante Mafia"
    And a localização tem a rua "Rua Exemplo", número 123, bairro "Centro", cidade "São Paulo", e CEP "12345-678"
    And o tipo de cozinha é "Italiana"
    And o restaurante tem 4 mesas, cada uma com 6 lugares
    When eu fizer o cadastro do restaurante
    Then o sistema deve retornar o restaurante cadastrado com sucesso
