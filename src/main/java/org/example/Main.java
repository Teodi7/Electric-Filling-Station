package org.example;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Electric-Charging-System-App\n");

        // "Controller"-ähnlich: alles über das Network (enthält alle Manager)
        ElectricChargingStationNetwork network = new ElectricChargingStationNetwork();

        LocationManager locationManager = network.getLocationManager();
        ChargerManager chargerManager = network.getChargerManager();
        PriceManager priceManager = network.getPriceManager();
        ClientAccountManager clientManager = network.getClientAccountManager();
        ChargingSessionManager sessionManager = network.getChargingSessionManager();
        InvoiceManager invoiceManager = network.getInvoiceManager();
       // BalanceInquiryManager balanceManager = new BalanceInquiryManager();

        // ------------------------------------------------------------
        System.out.println("E1 – Manage - Locations\n");

        // 10 Locations anlegen (dein LocationManager hat addLocation(..))
        Location loc1  = new Location(1,  "FH Technikum Wien",          "Höchstädtplatz 6");
        Location loc2  = new Location(2,  "Wien Westbahnhof",           "Mariahilfer Straße 100");
        Location loc3  = new Location(3,  "Wien Floridsdorf",           "Überfuhrstraße 3");
        Location loc4  = new Location(4,  "Wien Brigittenau",           "Briggittenauerlände 4");
        Location loc5  = new Location(5,  "Wien Donaustadt",            "Tokiostraße 5");
        Location loc6  = new Location(6,  "Wiener Gürtel",                     "Mariahilferstraße 6");
        Location loc7  = new Location(7,  "Wien Floridsdorf_2",         "Wehnhartgasse 7");
        Location loc8  = new Location(8,  "Niederösterreich Korneuburg","Kapaunplatz 8");
        Location loc9  = new Location(9,  "Wien Brigittenau_2",         "Wexstraße 9");
        Location loc10 = new Location(10, "Wien Donaustadt_2",          "Kagranerplatz 10");

        locationManager.addLocation(loc1);
        locationManager.addLocation(loc2);
        locationManager.addLocation(loc3);
        locationManager.addLocation(loc4);
        locationManager.addLocation(loc5);
        locationManager.addLocation(loc6);
        locationManager.addLocation(loc7);
        locationManager.addLocation(loc8);
        locationManager.addLocation(loc9);
        locationManager.addLocation(loc10);

        System.out.println("Alle Locations nach Erstellen:");
        for (Location l : locationManager.getAllLocations()) {
            System.out.println(l.getId() + " | " + l.getName() + " | " + l.getAddress());
        }
        System.out.println();

        // Delete Location (Manager hat kein delete -> wir entfernen direkt aus der Liste)
        System.out.println("Delete Location ID 2 (direkt aus getAllLocations entfernen):");
        Location toDelete = locationManager.getLocationById(2);
        if (toDelete != null) {
            locationManager.getAllLocations().remove(toDelete);
            System.out.println("Location 2 entfernt.");
        }
        System.out.println("Locations nach Löschen von ID 2:");
        for (Location l : locationManager.getAllLocations()) {
            System.out.println(l.getId() + " | " + l.getName());
        }
        System.out.println("\n");

            // UPDATE Location
        System.out.println("UPDATE: Location ID 1 aktualisieren:");
        Location updateLoc = locationManager.getLocationById(1);
        if (updateLoc != null) {
            updateLoc.setName("FH Technikum Wien – Garage");
            updateLoc.setAddress("Höchstädtplatz 6, Tiefgarage");
            System.out.println("Location 1 wurde aktualisiert.");
        }
        System.out.println();

    // READ ALL Locations (nach Update)
        System.out.println("READ ALL: Alle Locations nach UPDATE:");
        for (Location l : locationManager.getAllLocations()) {
            System.out.println(l.getId() + " | " + l.getName() + " | " + l.getAddress());
        }
        System.out.println();

        System.out.println();
        System.out.println();

        // ------------------------------------------------------------

        //TESTEN
        System.out.println("E2 – Manage - Chargers\n");

        int chargerIdCounter = 100;

        for (Location loc : locationManager.getAllLocations()) {

            // AC Charger
            Charger acCharger = new Charger(
                    chargerIdCounter++,
                    ChargerType.AC,
                    ChargerStatus.AVAILABLE,
                    loc
            );
            chargerManager.addCharger(acCharger);

            // DC Charger
            Charger dcCharger = new Charger(
                    chargerIdCounter++,
                    ChargerType.DC,
                    ChargerStatus.AVAILABLE,
                    loc
            );
            chargerManager.addCharger(dcCharger);
        }


        System.out.println("READ: Locations mit ihren Chargers & Status\n");

        for (Location loc : locationManager.getAllLocations()) {
            System.out.println("Location: " + loc.getName());

            List<Charger> chargersAtLocation =
                    chargerManager.getChargersByLocation(loc);

            for (Charger c : chargersAtLocation) {
                System.out.println(
                        "  - Charger " + c.getId()
                                + " | " + c.getType()
                                + " | " + c.getStatus()
                );
            }
            System.out.println();
        }


        for (Location loc : locationManager.getAllLocations()) {

            Charger acCharger = new Charger(
                    chargerIdCounter++,
                    ChargerType.AC,
                    ChargerStatus.AVAILABLE,
                    loc
            );
            chargerManager.addCharger(acCharger);

            Charger dcCharger = new Charger(
                    chargerIdCounter++,
                    ChargerType.DC,
                    ChargerStatus.AVAILABLE,
                    loc
            );
            chargerManager.addCharger(dcCharger);
        }

        System.out.println("Charger an Location FH Technikum Wien (VOR Update):");
        for (Charger c : chargerManager.getChargersByLocation(loc1)) {
            System.out.println(c.getId() + " | " + c.getType() + " | " + c.getStatus());
        }
        System.out.println();



        System.out.println("UPDATE: AC Charger an FH Technikum Wien -> OCCUPIED");

        // einen AC-Charger an loc1 holen
        Charger occupiedCharger = chargerManager.getChargersByLocation(loc1)
                .stream()
                .filter(c -> c.getType() == ChargerType.AC)
                .findFirst()
                .orElse(null);

        if (occupiedCharger != null) {
            occupiedCharger.setStatus(ChargerStatus.OCCUPIED);
        }
        System.out.println();


        System.out.println("Charger an Location FH Technikum Wien (NACH Update):");
        for (Charger c : chargerManager.getChargersByLocation(loc1)) {
            System.out.println(c.getId() + " | " + c.getType() + " | " + c.getStatus());
        }
        System.out.println();







        // ------------------------------------------------------------
        System.out.println("E3 – Manage - Prices\n");

        // Set Prices (bei dir: setPrice(location, type, pricePerKwh))
        priceManager.setPrice(loc1, ChargerType.AC, 0.25);
        priceManager.setPrice(loc1, ChargerType.DC, 0.40);

        System.out.println("Preise für Location FH Technikum Wien:");
        List<Price> pricesAtLoc1 = priceManager.getPricesForLocation(loc1);
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy     HH:mm:ss");
        for (Price p : pricesAtLoc1) {
            System.out.println(
                    "Type " + p.getChargerType()
                            + " | kWh=" + p.getPricePerKwh()
                            + " | date=" + p.getTimestamp().format(formatter)
            );

        }
        System.out.println();

        // Update Price (bei dir: updatePrice -> neuer Eintrag mit neuem Datum)
        System.out.println("Update AC Preis auf 0.30:");
        priceManager.updatePrice(loc1, ChargerType.AC, 0.30);

        Price latestAC = priceManager.getLatestPrice(loc1, ChargerType.AC);
        System.out.println(
                "Latest AC Price: kWh=" + latestAC.getPricePerKwh()
                        + " | date=" + latestAC.getTimestamp().format(formatter)
        );

        System.out.println("\n");

        // ------------------------------------------------------------
        System.out.println("E5/E6 – Client - Account & Balance\n");

        // Create Client Account (bei dir: addClient(new ClientAccount(...)))
        ClientAccount client = new ClientAccount(1, "Omar Ftaiti", "omar@example.com");
        clientManager.addClient(client);

        System.out.println("Account erstellt: " + client.getId() + " | " + client.getName() + " | " + client.getEmail());
        System.out.println("Start-Balance: " + client.getBalance());

        // TopUp
        clientManager.topUpBalance(1, 50.0);
        System.out.println("Balance nach TopUp: " + client.getBalance());

        // View Balance
        //double currentBalance = balanceManager.getCurrentBalance(client);
        //System.out.println("Balance laut BalanceInquiryManager: " + currentBalance);
        System.out.println("\n");

        // ------------------------------------------------------------
        System.out.println("E8 – Charge - EV\n");
        Charger ch100 = chargerManager.getChargerById(100);


        // Start Charging (ändert Status -> OCCUPIED)
        sessionManager.startCharging(ch100);
        System.out.println("Charging gestartet. Charger 100 Status: " + ch100.getStatus());

        // Session simulieren (energy setzen)
        ChargingSession session = new ChargingSession(ch100);
        session.setEnergyKwh(20.0);

        // Stop Charging (ändert Status -> AVAILABLE)
        sessionManager.stopCharging(ch100);
        System.out.println("Charging gestoppt. Charger 100 Status: " + ch100.getStatus());

        // Rechnung erstellen + Client belasten (macht InvoiceManager bei dir automatisch)
        Price priceToUse = priceManager.getLatestPrice(loc1, ch100.getType());
        Invoice invoice = invoiceManager.createInvoice(client, loc1, session.getEnergyKwh(), priceToUse);

        System.out.println("\nE9 – Invoice / History\n");



        System.out.println("Invoice date:     " + invoice.getTimestamp().format(formatter));
        System.out.println("Invoice location:   " + invoice.getLocationName());
        System.out.println("Energy (kWh):       " + invoice.getEnergyKwh());
        System.out.println("Price per kWh:      " + invoice.getPricePerKwh());
        System.out.println("Total amount (€):   " + invoice.getTotalAmount());

        System.out.println("\nClient Balance nach Invoice-Abzug: " + client.getBalance());

        // Optional: History zeigen
        System.out.println("\nClient hat " + client.getInvoices().size() + " Invoice(s) gespeichert.");
        System.out.println("Client TopUps: " + client.getTopUpHistory().size());
    }
}
