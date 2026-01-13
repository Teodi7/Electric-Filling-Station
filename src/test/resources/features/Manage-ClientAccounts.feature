Feature: Manage client accounts
  The owner can view and manage registered client accounts.
  Each client account contains a name, an email address and a balance.

  Scenario: View all client accounts
    Given the following clients exist:
      | id | name           | email                    |
      | 1  | Omar Ftaiti    | omar@example.com          |
      | 2  | Teodi Gregorian| teodi@example.com         |
      | 3  | Lukas Steiner  | lukas@example.com        |
    When the owner requests all client accounts
    Then the system should return 3 client accounts

  Scenario: Delete a client account
    Given a client with id 1, name "Omar Ftaiti", email "omar@example.com" exists
    When the owner deletes the client account with id 1
    Then the system should not contain a client account with id 1

    # edge case
  Scenario: Deleting a non-existing client account should not change the system
    Given the following clients exist:
      | id | name | email |
      | 1  | Omar | omar@example.com |
    When the owner deletes the client account with id 999
    And the owner requests all client accounts
    Then the system should return 1 client accounts
    And the system should not contain a client account with id 999
