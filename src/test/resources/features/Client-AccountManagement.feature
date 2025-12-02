Feature: Manage client account
  In order to use charging stations
  As a client
  I want to create an account and see my account data

  Scenario: Client creates an account and views his data
    Given a client with id 1, name "Teodi Gregorian" and email "teodi@example.com" registers in the system
    When the client requests his account data
    Then the system should show an active account with balance 0.0
