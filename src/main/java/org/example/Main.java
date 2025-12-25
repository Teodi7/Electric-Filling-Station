package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Electric-Charging-System-App\n");

        // gleich alle Manager hier anlegen
        LocationManager locationManager = new LocationManager();
        ChargerManager chargerManager = new ChargerManager();
        PriceManager priceManager = new PriceManager();
        ClientAccountManager accountManager = new ClientAccountManager();
        ChargingSessionManager sessionManager = new ChargingSessionManager();
        InvoiceManager invoiceManager = new InvoiceManager();
        BalanceInquiryManager balanceManager = new BalanceInquiryManager(accountManager);


        System.out.println(" E1 – Manage - Locations ");

        Location loc1 = locationManager.createLocation(
                1,
                "FH Technikum Wien",
                "Höchstädtplatz 6",
                "AVAILABLE"
        );

        Location loc2 = locationManager.createLocation(
                2,
                "Wien Westbahnhof",
                "Mariahilfer Straße 100",
                "AVAILABLE"
        );
        // Zusätzliche Locations anlegen, damit = 10 Locations
        Location loc3 = locationManager.createLocation(
                3,
                "Wien Floridsdorf ",
                "Überfuhrstraße 3",
                "AVAILABLE"
        );

        Location loc4 = locationManager.createLocation(
                4,
                "Wien Briggitenau",
                "Briggiternauerlände 4",
                "AVAILABLE"
        );

        Location loc5 = locationManager.createLocation(
                5,
                "Wien Donaustadt",
                "Tokiostraße 5",
                "AVAILABLE"
        );

        Location loc6 = locationManager.createLocation(
                6,
                "Gürtel",
                "Mariahilferstraße 6",
                "AVAILABLE"
        );

        Location loc7 = locationManager.createLocation(
                7,
                "Wien Floridsdorf_2",
                "Wehnhartgasse 7",
                "AVAILABLE"
        );

        Location loc8 = locationManager.createLocation(
                8,
                "Niederösterreich Korneuburg",
                "Kapaunplatz 8",
                "AVAILABLE"
        );

        Location loc9 = locationManager.createLocation(
                9,
                "Wien Briggiternau_2",
                "Wexstraße 9",
                "AVAILABLE"
        );

        Location loc10 = locationManager.createLocation(
                10,
                "Wien Donaustadt_2",
                "Kagranerplatz 10",
                "AVAILABLE"
        );

        System.out.println("Alle Locations nach Erstellen:");
        for (Location l : locationManager.getAllLocations()) {
            System.out.println(l.getLocationId() + " | " + l.getName() + " | " + l.getStatus());
        }
        System.out.println();
        System.out.println();

        // US1.3 – Update Location
        locationManager.updateLocation(
                1,
                "FH Technikum Wien – Garage",
                "Höchstädtplatz 6, Tiefgarage",
                "ACTIVE"
        );

        System.out.println("Location 1 nach Update:");
        Location updatedLoc1 = locationManager.findLocationById(1);
        System.out.println(updatedLoc1.getLocationId() + " | " + updatedLoc1.getName()
                + " | " + updatedLoc1.getAddress()
                + " | " + updatedLoc1.getStatus());
        System.out.println();

        // US1.4 – Delete Location
        locationManager.deleteLocation(2);

        System.out.println("Locations nach Löschen von ID 2:");
        for (Location l : locationManager.getAllLocations()) {
            System.out.println(l.getLocationId() + " | " + l.getName());
        }
        System.out.println();
        System.out.println();



        System.out.println("E2 – Manage - Chargers ");

        Charger ch100 = chargerManager.createCharger(
                100,
                ChargerType.AC,
                ChargerStatus.AVAILABLE,
                22.0,
                loc1
        );

        Charger ch101 = chargerManager.createCharger(
                101,
                ChargerType.DC,
                ChargerStatus.OUT_OF_SERVICE,
                50.0,
                loc1
        );

        System.out.println("Charger an Location 1:");
        for (Charger c : chargerManager.getChargersByLocation(loc1)) {
            System.out.println(c.getChargerId() + " | " + c.getType() + " | " + c.getStatus());
        }
        System.out.println();
        System.out.println();

        // US2.3 – Update Charger (Status ändern)
        chargerManager.updateChargerStatus(101, ChargerStatus.AVAILABLE);

        System.out.println("Charger 101 nach Status-Update:");
        Charger updatedCh101 = chargerManager.findChargerById(101);
        System.out.println(updatedCh101.getChargerId() + " | " + updatedCh101.getStatus());
        System.out.println();

        // US2.4 – Delete Charger
        chargerManager.deleteCharger(101);

        System.out.println("Charger an Location 1 nach Löschen von 101:");
        for (Charger c : chargerManager.getChargersByLocation(loc1)) {
            System.out.println(c.getChargerId() + " | " + c.getStatus());
        }
        System.out.println();
        System.out.println();



        System.out.println("E3 – Manage - Prices");

        Price priceAC = new Price(loc1, ChargerType.AC, 0.25, 0.10);
        Price priceDC = new Price(loc1, ChargerType.DC, 0.40, 0.20);

        priceManager.addPrice(priceAC);
        priceManager.addPrice(priceDC);

        System.out.println("Preise für Location 1:");
        List<Price> pricesAtLoc1 = priceManager.getPricesByLocation(loc1);
        for (Price p : pricesAtLoc1) {
            System.out.println("Type " + p.getChargerType()
                    + " | kWh=" + p.getPricePerKwh()
                    + " | min=" + p.getPricePerMinute());
        }
        System.out.println();

        // US3.2 – Update Prices
        priceManager.updatePrice(loc1, ChargerType.AC, 0.30, 0.15);

        Price updatedPriceAC = priceManager.getPrice(loc1, ChargerType.AC);
        System.out.println("AC-Preis nach Update: kWh=" + updatedPriceAC.getPricePerKwh()
                + " | min=" + updatedPriceAC.getPricePerMinute());
        System.out.println();
        System.out.println();



        System.out.println("E5/E6 – Client - Account & Balance");

        // US5.1 – Create Client Account
        ClientAccount clientAcc = accountManager.createClientAccount(
                1,
                "Teodi Gregorian",
                "teodi@example.com"
        );

        System.out.println("Account erstellt für: " + clientAcc.getName());
        System.out.println("Start-Balance: " + clientAcc.getBalance());
        System.out.println();

        // US5.2 – Update Client Account
        clientAcc.updateContactData("Omar Ftaiti", "omar@example.com");
        ClientAccount shownAcc = accountManager.getAccountByClientId(1);

        System.out.println("Account nach Update:");
        System.out.println("Name:  " + shownAcc.getName());
        System.out.println("Email: " + shownAcc.getEmail());
        System.out.println();

        // US6.2 – Top-Up Balance
        accountManager.topUpBalance(1, 50.0);
        System.out.println("Balance nach Top-Up: " + clientAcc.getBalance());

        // US6.1 – View Balance (über BalanceInquiryManager)
        double currentBalance = balanceManager.getCurrentBalance(1);
        System.out.println("Balance laut View-Balance-Funktion: " + currentBalance);
        System.out.println();



        System.out.println("E8 – Charge - EV");

        // AC-Charger ch100 und den Preis updatedPriceAC zusammen
        ChargingSession session = sessionManager.startSession(clientAcc, ch100, 5001);
        System.out.println("Charging Session gestartet mit ID: " + session.getSessionId());

        session.setEnergyKWh(20.0);

        // Session unterbrechen, dauerschleife
        sessionManager.stopSession(session.getSessionId());
        System.out.println("Charging Session gestoppt.");

        // Kosten berechnen
        double chargingCost = session.calculateCost(updatedPriceAC);
        System.out.println("Berechnete Ladekosten: " + chargingCost + " €");

        // Zahlung durchführen
        if (clientAcc.canPay(chargingCost)) {
            clientAcc.debit(chargingCost);
            System.out.println("Zahlung erfolgreich. Neue Balance: " + clientAcc.getBalance());
        } else {
            System.out.println("Nicht genug Guthaben für die Ladekosten!");
        }
        System.out.println();
        System.out.println();



        System.out.println("E9 – Review - History AND Invoice");

        // Rechnung für den Client erstellen
        Invoice invoice = invoiceManager.createInvoice(clientAcc);

        // Invoice Item für die Ladesession
        InvoiceItem item = new InvoiceItem(
                1,
                "Charging Session at " + loc1.getName() + " (20 kWh)",
                chargingCost
        );
        invoice.addItem(item);

        // Ausgabe der Rechnung
        System.out.println("Rechnung ID: " + invoice.getInvoiceId());
        System.out.println("Rechnungsdatum: " + invoice.getDate());
        System.out.println("Anzahl Items: " + invoice.getItems().size());
        System.out.println("Gesamtbetrag: " + invoice.getTotalAmount() + " €");
        System.out.println();

        // US6.3 – Withdraw Balance (Restguthaben auszahlen)
        double withdrawn = accountManager.withdrawAllBalance(1);
        System.out.println("Ausgezahltes Restguthaben: " + withdrawn + " €");
        System.out.println("Balance nach Auszahlung: " + clientAcc.getBalance());



    }
}
