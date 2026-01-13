Feature: Client account top up
  Clients can add credit to their account.

  Scenario: Client tops up account balance
    Given a client with id 1, name "Omar Ftaiti ", email "omar@example.com" exists
    When the client with id 1 tops up the balance by 50.00 EUR
    Then the client balance should be 50.00 EUR

    # edge case
  Scenario: Topping up with a negative amount should not change the balance
    Given a client with id 1, name "Omar", email "omar@example.com" exists
    When the client with id 1 tops up the balance by -10.0 EUR
    Then the client balance should be 0.0 EUR
