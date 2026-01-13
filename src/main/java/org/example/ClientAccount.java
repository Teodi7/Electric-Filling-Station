package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientAccount {

    private int id;
    private String name;
    private String email;
    private double balance;

    private List<TopUp> topUpHistory = new ArrayList<>();
    private List<Invoice> invoices = new ArrayList<>();

    public ClientAccount(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = 0.0;
    }
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void topUp(double amount) {
        balance += amount;
        topUpHistory.add(new TopUp(LocalDate.now(), amount));
    }

    public void charge(double amount) {
        balance -= amount;
    }

    public List<TopUp> getTopUpHistory() {
        return topUpHistory;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
