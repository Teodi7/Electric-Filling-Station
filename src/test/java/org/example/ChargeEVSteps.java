package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ChargeEVSteps {

    private static Invoice lastInvoice;

    public static Invoice getLastInvoice() {
        return lastInvoice;
    }


    @Given("the charger with id {int} is out of service")
    public void the_charger_with_id_is_out_of_service(Integer chargerId) {
        Charger charger = LocationSteps.getNetwork()
                .getChargerManager()
                .getChargerById(chargerId);

        if (charger != null) {
            charger.setStatus(ChargerStatus.OUT_OF_SERVICE);
        }
    }


    @When("the client charges {double} kWh at {string} using charger {int}")
    public void the_client_charges_k_wh_at_using_charger(
            Double energyKwh,
            String locationName,
            Integer chargerId
    ) {
        ElectricChargingStationNetwork network = LocationSteps.getNetwork();

        // Charger holen
        Charger charger = network.getChargerManager().getChargerById(chargerId);
        if (charger == null || charger.getStatus() != ChargerStatus.AVAILABLE) {
            return; // Edge case: Charger nicht verfügbar
        }

        // Location holen
        Location location = network.getLocationManager().getLocationByName(locationName);
        if (location == null) {
            return;
        }

        // Client holen (bei dir immer Client ID = 1)
        ClientAccount client = network.getClientAccountManager().getClientById(1);
        if (client == null) {
            return;
        }

        // WICHTIG: Latest Price mit Uhrzeit + ChargerType
        Price latestPrice = network.getPriceManager()
                .getLatestPrice(location, charger.getType());

        if (latestPrice == null) {
            return; // Edge case: kein Preis vorhanden
        }

        // Invoice mit Zeit + AC/DC erzeugen
        lastInvoice = network.getInvoiceManager()
                .createInvoice(client, location, energyKwh, latestPrice);
    }
    public static void resetLastInvoice() {
        lastInvoice = null;
    }

}
