package org.example;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        /* UM die anderen Klassen zu testen
        System.out.println("=== Electric Charging System gestartet ===\n");

        // --- Manager-Klassen erstellen ---
        LocationManager locationManager = new LocationManager();
        ChargerManager chargerManager = new ChargerManager();
        PriceManager priceManager = new PriceManager();
        ClientAccountManager accountManager = new ClientAccountManager();
        ChargingSessionManager sessionManager = new ChargingSessionManager();
        InvoiceManager invoiceManager = new InvoiceManager();

        // --- Beispiel-Location anlegen ---
        Location loc = locationManager.createLocation(
                1,
                "Wien West",
                "Mariahilfer Straße 100",
                "ACTIVE"
        );

        System.out.println("Location erstellt: " + loc.getName());

        // --- Charger anlegen (jetzt mit Enums) ---
        Charger charger = chargerManager.createCharger(
                1,
                ChargerType.AC,
                ChargerStatus.AVAILABLE,
                22.0,
                loc
        );

        System.out.println("Charger hinzugefügt: " + charger.getChargerId());

        // --- Price mit Enum ChargerType ---
        Price price = new Price(1, ChargerType.AC, 0.30, 0.05);
        priceManager.addPrice(price);
        System.out.println("Preis für AC hinterlegt.");

        // --- Client anlegen ---
        Client client = new Client(1, "Max Mustermann", "max@example.com");
        ClientAccount account = accountManager.createClientAccount(client);

        System.out.println("Kunde erstellt: " + client.getName());
        System.out.println("Start-Balance: " + account.getBalance());

        // --- Guthaben aufladen ---
        accountManager.topUpBalance(account.getAccountId(), 20.0);
        System.out.println("Neue Balance nach Top-Up: " + account.getBalance());

        // --- Ladevorgang starten ---
        ChargingSession session = sessionManager.startSession(account, charger, 1001);
        System.out.println("Session gestartet: ID " + session.getSessionId());

        // Simulierte Ladeenergie
        session.setEnergyKWh(15.0);

        // Session stoppen
        sessionManager.stopSession(session.getSessionId());
        System.out.println("Session gestoppt.");

        // Kosten berechnen
        double cost = session.calculateCost(price);
        System.out.println("Kosten berechnet: " + cost + " €");

        // Zahlung durchführen
        if (account.canPay(cost)) {
            account.debit(cost);
            System.out.println("Zahlung erfolgreich! Neue Balance: " + account.getBalance());
        } else {
            System.out.println("Nicht genug Guthaben!");
        }

        // --- Rechnung erstellen ---
        Invoice invoice = invoiceManager.createInvoice(account);
        invoice.addItem(new InvoiceItem(1, "Laden: 15 KWh", cost));

        System.out.println("\n--- Rechnung ---");
        System.out.println("Rechnungsnummer: " + invoice.getInvoiceId());
        System.out.println("Gesamtbetrag: " + invoice.getTotalAmount() + " €");
    }

         */





        // Für Aufgabe IA6
        //LocationSteps!!!

        LocationManager locationManager = new LocationManager();

        // Scenario 1: Create a new location
        Location loc1 = locationManager.createLocation(
                1,
                "FH-Technikum",
                "Höchstädtplatz 6",
                "AVAILABLE"
        );

        System.out.println("Created Location:");
        System.out.println(loc1.getLocationId() + " - " + loc1.getName() + ", " + loc1.getAddress() + " (" + loc1.getStatus() + ")");

        // Scenario 2: Read all locations
        locationManager.createLocation(
                2,
                "City Center",
                "1010 Vienna",
                "AVAILABLE"
        );

        System.out.println("\nAll Locations:");
        for (Location loc : locationManager.getAllLocations()) {
            System.out.println(loc.getLocationId() + ": " + loc.getName() + " - " + loc.getAddress() + " | " + loc.getStatus());
        }



        // ChargerSteps!!!


        ChargerManager chargerManager = new ChargerManager();

        chargerManager.createCharger(100, ChargerType.AC, ChargerStatus.AVAILABLE, 22.0, loc1);
        chargerManager.createCharger(101, ChargerType.DC, ChargerStatus.OUT_OF_SERVICE, 50.0, loc1);

        System.out.println("Chargers at " + loc1.getName() + ":");
        for (Charger c : chargerManager.getChargersByLocation(loc1)) {
            System.out.println(c.getChargerId() + " - " + c.getType() + " - " + c.getStatus());
        }



        //ClientAccountSteps

        ClientAccountManager manager = new ClientAccountManager();

        ClientAccount a1 = manager.createClientAccount(1, "Omar", "omar@mail.com");
        ClientAccount a2 = manager.createClientAccount(2, "Teodi", "teodi@mail.com");

        manager.topUpBalance(1, 50);

        System.out.println("Account 1 balance: " + a1.getBalance());
        System.out.println("Account 2 name: " + a2.getName());



        // Client perspective: create and view own account

        //ClientAccountManager manager = new ClientAccountManager();

// Ein ClientAccount wird direkt über den Manager erstellt:
        ClientAccount clientAcc = manager.createClientAccount(
                1,
                "Max Mustermann",
                "max@example.com"
        );

        System.out.println("\nClient perspective:");
        System.out.println("Client " + clientAcc.getAccountId() + " created an account.");
        System.out.println("Account status: " + clientAcc.getStatus());
        System.out.println("Account balance: " + clientAcc.getBalance());


        //Client perspective: top up account

        ClientAccountManager clientAccountManager = new ClientAccountManager();

        //Account direkt anlegen (kein Client mehr!)
        ClientAccount acc3 = clientAccountManager.createClientAccount(
                3,
                "Arian Elijah",
                "arian@example.com"
        );

        //Anfangsguthaben setzen
        acc3.credit(50.0);

        System.out.println("\nBefore top up: balance = " + acc3.getBalance());

        //Guthaben aufladen
        clientAccountManager.topUpBalance(acc3.getAccountId(), 20.0);

        System.out.println("After top up: balance = " + acc3.getBalance());



    }

}
