Feature: View client balance
  Clients can view their current account balance.

  Scenario: View current balance
    Given a client with id 1, name "Omar Ftaiti", email "omar@example.com" exists
    And the client with id 1 tops up the balance by 30.00 EUR
    When the client requests the current balance
    Then the balance should be 30.00 EUR

    # edge case
  Scenario: Requesting balance for a new client should return 0 EUR
    Given a client with id 1, name "Omar", email "omar@example.com" exists
    When the client requests the current balance
    Then the balance should be 0.0 EUR
