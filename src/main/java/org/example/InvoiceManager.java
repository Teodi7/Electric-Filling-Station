package org.example;

import java.time.LocalDate;

public class InvoiceManager {

    public Invoice createInvoice(ClientAccount client,
                                 Location location,
                                 double energyKwh,
                                 Price price) {

        Invoice invoice = new Invoice(
                LocalDate.now(),
                location.getName(),
                energyKwh,
                price.getPricePerKwh()
        );

        client.addInvoice(invoice);
        client.charge(invoice.getTotalAmount());

        return invoice;
    }
}
