package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ChargeEVSteps {

    private static Invoice lastInvoice;

    public static Invoice getLastInvoice() {
        return lastInvoice;
    }

    public static void resetLastInvoice() {
        lastInvoice = null;
    }
    //für edge case ergänzt
    @Given("the charger with id {int} is out of service")
    public void the_charger_with_id_is_out_of_service(Integer chargerId) {
        Charger charger = LocationSteps.getNetwork()
                .getChargerManager()
                .getChargerById(chargerId);

        if (charger != null) {
            charger.setStatus(ChargerStatus.OUT_OF_SERVICE);
        }
    }


    //für edge case überarbeitet
    @When("the client charges {double} kWh at {string} using charger {int}")
    public void the_client_charges_k_wh_at_using_charger(
            Double energy, String locationName, Integer chargerId) {

        ElectricChargingStationNetwork network = LocationSteps.getNetwork();

        Charger charger = network.getChargerManager().getChargerById(chargerId);

        //Edge case: Charger nicht verfügbar → kein Charging, keine Invoice
        if (charger == null || charger.getStatus() != ChargerStatus.AVAILABLE) {
            return;
        }

        Location location = network.getLocationManager().getLocationByName(locationName);
        if (location == null) {
            return;
        }

        ClientAccount client = network.getClientAccountManager().getClientById(1);
        if (client == null) {
            return;
        }

        Price price = network.getPriceManager().getLatestPrice(location, charger.getType());
        if (price == null) {
            return;
        }

        // Normalfall
        lastInvoice = network.getInvoiceManager()
                .createInvoice(client, location, energy, price);
    }



}
