Feature: Manage client account
  In order to use charging stations
  As a client
  I want to create an account and see my account data

  Scenario: Client creates an account and views his data
    Given a client with id 1, name "Teodi Gregorian" and email "teodi@example.com" registers in the system
    When the client requests his account data
    Then the system should show an active account with balance 0.0

  Scenario: Client updates his account data
    Given a client with id 2, name "Omar" and email "omar@example.com" registers in the system
    When the client updates his account data to name "O. Ftaaiti" and email "o.ftaaiti@example.com"
    Then the system should show client account with id 2 having name "O. Ftaaiti" and email "o.ftaaiti@example.com"

  Scenario: Client deletes his account
    Given a client with id 3, name "Sarah" and email "sarah@example.com" registers in the system
    When the client deletes his account with id 3
    Then the system should not show any account with id 3