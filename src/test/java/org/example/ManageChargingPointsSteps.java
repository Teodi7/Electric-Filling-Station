package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ManageChargingPointsSteps {

    private List<Charger> lastResult;

    @When("the owner adds a charger with id {int} of type AC to location {string}")
    public void the_owner_adds_a_charger_with_id_of_type_ac_to_location(Integer id, String locationName) {
        Location location = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);

        // Edge case: Location existiert nicht, das System soll unverändert bleiben
        if (location == null) {
            return;
        }

        Charger charger = new Charger(id, ChargerType.AC, ChargerStatus.AVAILABLE, location);
        LocationSteps.getNetwork().getChargerManager().addCharger(charger);
    }



    @When("the owner adds a charger with id {int} of type DC to location {string}")
    public void the_owner_adds_a_charger_with_id_of_type_dc_to_location(Integer id, String locationName) {
        Location location = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);

        // Edge case: Location existiert nicht, System soll gleich bleiben
        if (location == null) {
            return;
        }

        Charger charger = new Charger(id, ChargerType.DC, ChargerStatus.AVAILABLE, location);
        LocationSteps.getNetwork().getChargerManager().addCharger(charger);
    }


    @Then("the location {string} should have {int} chargers")
    public void the_location_should_have_chargers(String locationName, Integer count) {
        Location location = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);
        Assertions.assertNotNull(location);
        Assertions.assertEquals(count, location.getChargers().size());
    }

    @Given("the location {string} has a charger with id {int} of type AC")
    public void the_location_has_a_charger_with_id_of_type_ac(String locationName, Integer id) {
        Location location = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);
        Assertions.assertNotNull(location);

        Charger charger = new Charger(id, ChargerType.AC, ChargerStatus.AVAILABLE, location);
        LocationSteps.getNetwork().getChargerManager().addCharger(charger);
    }

    @When("the owner requests all chargers for location {string}")
    public void the_owner_requests_all_chargers_for_location(String locationName) {
        Location location = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);
        Assertions.assertNotNull(location);

        lastResult = LocationSteps.getNetwork().getChargerManager().getChargersByLocation(location);
    }

    @Then("the system should return {int} charger")
    public void the_system_should_return_charger(Integer expected) {
        Assertions.assertNotNull(lastResult);
        Assertions.assertEquals(expected, lastResult.size());
    }
}
