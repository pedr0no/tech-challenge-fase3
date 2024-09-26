@ReservationTest
Feature: Fazer uma reserva no restaurante

  Scenario: Fazer uma reserva no restaurante "teste03"
    Given o nome do restaurante é "teste03"
    When eu fizer o cadastro da reserva com o email "daniela.ono@hotmail.com" e a quantidade de pessoas 15
    Then a data da reserva é "2024-09-25" realizada com sucessso

