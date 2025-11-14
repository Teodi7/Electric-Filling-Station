package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private int invoiceId;
    private LocalDate issueDate;
    private double totalAmount;
    private List<InvoiceItem> items = new ArrayList<>();

    public Invoice(int invoiceId, LocalDate issueDate) {
        this.invoiceId = invoiceId;
        this.issueDate = issueDate;
    }

    public Invoice() {
        this.issueDate = LocalDate.now();
    }



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
        this.items = items;
        calculateTotal();
    }
}
