@Tag("integration")
Feature: Avaliar restaurante

  Scenario: Criar uma avaliação para o restaurante "Taco Bell"
    Given o cliente com o email "daniela.ono@hotmail.com" avalia o restaurante "Taco Bell"
    And a nota da avaliação é 4.5
    And o comentário da avaliação é "Comida excelente!"
    When a avaliação for criada
