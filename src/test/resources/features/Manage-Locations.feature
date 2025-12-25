Feature: Manage locations
  In order to operate multiple charging stations
  As the owner
  I want to create and view locations

  Scenario: Owner creates a new location
    Given there is no location with id 1
    When the owner creates a new location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    Then the system should contain a location with id 1
    And the location name should be "FH-Technikum"
    And the location address should be "Höchstädtplatz 6"
    And the location status should be "AVAILABLE"

  Scenario: Owner views all locations
    Given there is an existing location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    And there is an existing location with id 2, name "City Center", address "1010 Vienna" and status "AVAILABLE"
    When the owner requests the list of locations
    Then the system should return 2 locations


  Scenario: Owner updates an existing location
    Given there is an existing location with id 1, name "Schulschiff", address "Donauinsel" and status "AVAILABLE"
    When the owner updates the location with id 1 to name "Schulschiff", address "Donauinsel" and status "OUT_OF_SERVICE"
    Then the system should contain a location with id 1
    And the location name should be "Schulschiff"
    And the location address should be "Donauinsel"
    And the location status should be "OUT_OF_SERVICE"

  Scenario: Owner deletes an existing location
    Given there is an existing location with id 2, name "Rathaus", address "Rathausplatz 1 " and status "AVAILABLE"
    When the owner deletes the location with id 2
    Then the system should not contain a location with id 2
