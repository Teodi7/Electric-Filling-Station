Feature: Top up account
  In order to continue using charging stations
  As a client
  I want to top up my account balance

  Scenario: Client successfully tops up his account
    Given a client with id 1, name "Teodi Gregorian" and email "teodi@example.com" has an active account with balance 50.0
    When the client tops up his account by 20.0
    Then the topped up account balance should be 70.0