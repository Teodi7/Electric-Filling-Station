package org.example;

import java.time.LocalDate;

public class TopUp {

    private LocalDate date;
    private double amount;

    public TopUp(LocalDate date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
