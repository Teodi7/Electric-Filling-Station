package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceManager {

    private List<Invoice> invoices = new ArrayList<>();

    public Invoice createInvoice(ClientAccount account) {
        Invoice invoice = new Invoice(invoices.size() + 1, LocalDate.now());
        invoices.add(invoice);
        return invoice;
    }

    public List<Invoice> getInvoicesByAccount(ClientAccount account) {
        return invoices;
    }
}
