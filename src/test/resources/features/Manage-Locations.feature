Feature: Manage charging locations
  The owner can create, view, update and delete charging locations.
  A location represents a real place with a name and address.

  Scenario: Create a new charging location
    Given there is no location with id 1
    When the owner creates a location with id 1, name "FH-Technikum", address "Höchstädtplatz 6"
    Then the system should contain a location with id 1
    And the location name should be "FH-Technikum"
    And the location address should be "Höchstädtplatz 6"

  Scenario: View all charging locations
    Given the following locations exist:
      | id | name                        | address                |
      | 1  | FH-Technikum                | Höchstädtplatz 6       |
      | 2  | Hauptbahnhof Wien           | Am Hauptbahnhof 1      |
      | 3  | Wien Floridsdorf            | Überfuhrstraße 3       |
      | 3  | Wien Brigittenau            | Briggittenauerlände 4  |
      | 5  | Wien Donaustadt             | Tokiostraße 5          |
      | 6  | Wiener Gürtel               | Mariahilferstraße 6    |
      | 7  | Wien Floridsdorf_2          | Wehnhartgasse 7        |
      | 8  | Niederösterreich Korneuburg | Kapaunplatz 8          |
      | 9  | Wien Brigittenau_2          | Wexstraße 9            |
      | 10 | Wien Donaustadt_2           | Kagranerplatz 10       |

    When the owner requests all locations
    Then the system should return 10 locations

  Scenario: Update a charging location
    Given a location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" exists
    When the owner updates the location with id 1 to name "FH-Technikum Wien", address "Höchstädtplatz 6"
    Then the location with id 1 should have name "FH-Technikum Wien"

  Scenario: Delete a charging location
    Given a location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" exists
    When the owner deletes the location with id 1
    Then the system should not contain a location with id 1

            #edge case
  Scenario: Deleting a non-existing location should not change the system
    Given the following locations exist:
      | id | name         | address          |
      | 1  | FH-Technikum | Höchstädtplatz 6 |
    When the owner deletes the location with id 999
    Then the system should return 1 locations
    And the system should contain a location with id 1

