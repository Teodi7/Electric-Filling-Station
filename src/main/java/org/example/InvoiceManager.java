package org.example;

public class InvoiceManager {

    public Invoice createInvoice(
            ClientAccount client,
            Location location,
            double energyKwh,
            Price price
    ) {

        if (client == null || location == null || price == null) {
            return null;
        }

        double total = energyKwh * price.getPricePerKwh();

        // Edge Case: nicht genug Guthaben
        if (client.getBalance() < total) {
            return null;
        }

        // Guthaben abbuchen (direkt, passend zu deiner Klasse)
        client.withdraw(total);

        // Invoice mit Zeit + AC/DC erzeugen
        Invoice invoice = new Invoice(
                location.getName(),
                energyKwh,
                price.getPricePerKwh(),
                price.getChargerType()
        );

        client.addInvoice(invoice);
        return invoice;
    }
}
