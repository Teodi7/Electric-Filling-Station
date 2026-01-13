Feature: Client account management
  Clients must be registered before charging.

  Scenario: Register multiple client accounts
    Given the following clients exist:
      | id | name           | email                    |
      | 1  | Omar Ftaiti    | omar@example.com          |
      | 2  | Teodi Gregorian| Teodi@example.com         |
      | 3  | Lukas Steiner  | lukas@example.com        |
      | 4  | Maria Manikan  | maria@example.com        |
      | 5  | Thomas Gruber  | thomas@example.com       |
      | 6  | Julia Sedeno   | julia@example.com        |
      | 7  | David Hofer    | david@example.com        |
    When the owner requests all client accounts
    Then the system should return 7 client accounts

       # edge case
  Scenario: Requesting client accounts when none exist should return 0
    When the owner requests all client accounts
    Then the system should return 0 client accounts
