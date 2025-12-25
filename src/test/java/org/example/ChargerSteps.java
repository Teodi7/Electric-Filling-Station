package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChargerSteps {

    private LocationManager locationManager = new LocationManager();
    private ChargerManager chargerManager = new ChargerManager();
    private List<Charger> result;

    @Given("the location with id {int} has a charger with id {int}, type {string} and status {string}")
    public void theLocationHasCharger(int locationId, int chargerId, String type, String status) {
        Location loc = locationManager.findLocationById(locationId);
        if (loc == null) {
            loc = locationManager.createLocation(locationId, "AutoCreated", "AutoAddress", "AVAILABLE");
        }

        //charger anlegen
        ChargerType chargerType = ChargerType.valueOf(type);
        ChargerStatus chargerStatus = ChargerStatus.valueOf(status);

        chargerManager.createCharger(chargerId, chargerType, chargerStatus, 22.0, loc);

        //loc überhaupt vorhanden?
        assertNotNull(loc, "Location should not be null");
    }

    @When("the owner requests the chargers for location with id {int}")
    public void theOwnerRequestsChargers(int locationId) {
        result = chargerManager.getChargersByLocation(
                locationManager.findLocationById(locationId)
        );
    }

    @Then("the system should return {int} chargers for location with id {int}")
    public void theSystemShouldReturnChargers(int expected, int locationId) {
        assertEquals(expected, result.size());
    }

    //update charger
    @When("the owner updates the charger with id {int} to status {string}")
    public void the_owner_updates_the_charger_with_id_to_status(Integer chargerId, String statusText) {

        Charger charger = chargerManager.findChargerById(chargerId);

        // Falls Charger existiert muss ich Status setzen
        if (charger != null) {
            charger.setStatus(ChargerStatus.valueOf(statusText));
        }
    }

    @Then("the system should show a charger with id {int} having status {string}")
    public void the_system_should_show_a_charger_with_id_having_status(Integer chargerId, String expectedStatus) {

        Charger charger = chargerManager.findChargerById(chargerId);


        assertNotNull(charger);
        assertEquals(ChargerStatus.valueOf(expectedStatus), charger.getStatus());
    }

        //delete charger
    @When("the owner deletes the charger with id {int}")
    public void the_owner_deletes_the_charger_with_id(Integer chargerId) {
        chargerManager.deleteCharger(chargerId);
    }


    @Then("the system should not contain a charger with id {int} for location with id {int}")
    public void the_system_should_not_contain_a_charger_with_id_for_location_with_id(Integer chargerId, Integer locationId) {

        Charger charger = chargerManager.findChargerById(chargerId);


        assertNull(charger);
    }


}
