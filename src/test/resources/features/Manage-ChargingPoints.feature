Feature: Manage charging points
  In order to manage the chargers at my locations
  As the owner
  I want to create chargers and view all chargers for a location

  Scenario: Owner creates a charger for a location
    Given there is a location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    When the owner creates a charger with id 100, type "AC" and status "AVAILABLE" for location with id 1
    Then the system should show 1 charger for location with id 1

  Scenario: Owner views chargers of a location
    Given there is a location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    And the location with id 1 has a charger with id 100, type "AC" and status "AVAILABLE"
    And the location with id 1 has a charger with id 101, type "DC" and status "OUT_OF_SERVICE"
    When the owner requests the chargers for location with id 1
    Then the system should return 2 chargers for location with id 1

  Scenario: Owner updates an existing charger
    Given there is a location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    And the location with id 1 has a charger with id 100, type "AC" and status "AVAILABLE"
    When the owner updates the charger with id 100 to status "OUT_OF_SERVICE"
    Then the system should show a charger with id 100 having status "OUT_OF_SERVICE"

  Scenario: Owner deletes an existing charger
    Given there is a location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    And the location with id 1 has a charger with id 101, type "DC" and status "AVAILABLE"
    When the owner deletes the charger with id 101
    Then the system should not contain a charger with id 101 for location with id 1
