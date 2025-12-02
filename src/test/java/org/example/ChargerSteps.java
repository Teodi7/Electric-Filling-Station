package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.List;

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
        Assertions.assertNotNull(loc, "Location should not be null");
    }

    @When("the owner requests the chargers for location with id {int}")
    public void theOwnerRequestsChargers(int locationId) {
        result = chargerManager.getChargersByLocation(
                locationManager.findLocationById(locationId)
        );
    }

    @Then("the system should return {int} chargers for location with id {int}")
    public void theSystemShouldReturnChargers(int expected, int locationId) {
        Assertions.assertEquals(expected, result.size());
    }
}
