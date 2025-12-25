Feature: Client views current balance
  As a client,
  I want to view my current balance,
  so that I know whether I have enough credit for a charging session.

  Scenario: Client views current balance of an existing account
    Given an existing client account with id 1 and balance 50.0
    When the client checks their current balance
    Then the system shows a balance of 50.0
