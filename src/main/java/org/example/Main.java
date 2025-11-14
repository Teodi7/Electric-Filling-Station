package org.example;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

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
}
