package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// E9 – Review History
// US9.1 – View Invoice Items

public class Invoice {

    private int invoiceId;
    private LocalDate issueDate;
    private double totalAmount;
    private List<InvoiceItem> items = new ArrayList<>();


    private ClientAccount account;


    public Invoice(int invoiceId, LocalDate issueDate, ClientAccount account) {
        this.invoiceId = invoiceId;
        this.issueDate = issueDate;
        this.account = account;
    }


    public Invoice() {
        this.issueDate = LocalDate.now();
    }

    //  US9.1 – Invoice Items hinzufügen / Gesamtbetrag berechnen ---

    public void addItem(InvoiceItem item) {
        if (item != null) {
            items.add(item);
            calculateTotal();
        }
    }

    public double calculateTotal() {
        double sum = 0.0;
        for (InvoiceItem item : items) {
            sum += item.getAmount();
        }
        this.totalAmount = sum;
        return totalAmount;
    }

    // Getter / Setter

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items != null ? items : new ArrayList<>();
        calculateTotal();
    }

    public ClientAccount getAccount() {
        return account;
    }

    public void setAccount(ClientAccount account) {
        this.account = account;
    }



    // Für Main, damit es funktioniert
    public LocalDate getDate() {
        return issueDate;
    }

    // ebenfalls für meine Main
    public double getAmount() {
        return totalAmount;
    }
}
