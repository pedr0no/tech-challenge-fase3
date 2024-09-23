Feature: Cadastro de cliente

  Scenario: Cadastrar um novo cliente
    Given o nome do cliente é gerado aleatoriamente
    And o email do cliente é gerado aleatoriamente
    When eu fizer o cadastro do cliente
    Then o sistema deve retornar o cliente cadastrado com sucesso