Feature: Manage client accounts
  In order to keep track of customers
  As the owner
  I want to view all client accounts

  Scenario: Owner views all client accounts
    Given there is a client with id 1, name "Teodi Gregorian" and email "teodi@example.com" and an active account with balance 0.0
    And there is a client with id 2, name "Omar" and email "omar@example.com" and an active account with balance 10.0
    When the owner requests the list of client accounts
    Then the system should return 2 client accounts