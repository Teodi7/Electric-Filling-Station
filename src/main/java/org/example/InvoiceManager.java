package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


 //Verwalter für alle Rechnungen.
 //US9.1 – View Invoice Items
public class InvoiceManager {

    private List<Invoice> invoices = new ArrayList<>();


    public Invoice createInvoice(ClientAccount account) {
        int nextId = invoices.size() + 1;
        Invoice invoice = new Invoice(nextId, LocalDate.now(), account);
        invoices.add(invoice);
        return invoice;
    }


    public List<Invoice> getInvoicesByAccount(ClientAccount account) {
        List<Invoice> result = new ArrayList<>();
        if (account == null) {
            return result;
        }

        for (Invoice invoice : invoices) {
            if (invoice.getAccount() == account) {
                result.add(invoice);
            }
        }
        return result;
    }

    //alle Rechnungen
    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoices);
    }
}
