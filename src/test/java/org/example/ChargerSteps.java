package org.example;

import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;

public class ChargerSteps {

    @Given("a charger with id {int} of type AC exists at {string}")
    public void a_charger_with_id_of_type_ac_exists_at(Integer id, String locationName) {
        Location location = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);
        Assertions.assertNotNull(location, "Location not found: " + locationName);

        Charger charger = new Charger(id, ChargerType.AC, ChargerStatus.AVAILABLE, location);
        LocationSteps.getNetwork().getChargerManager().addCharger(charger);
    }
}
