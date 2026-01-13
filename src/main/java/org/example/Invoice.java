package org.example;

import java.time.LocalDate;

public class Invoice {

    private LocalDate date;
    private String locationName;
    private double energyKwh;
    private double pricePerKwh;
    private double totalAmount;

    public Invoice(LocalDate date, String locationName, double energyKwh, double pricePerKwh) {
        this.date = date;
        this.locationName = locationName;
        this.energyKwh = energyKwh;
        this.pricePerKwh = pricePerKwh;
        this.totalAmount = energyKwh * pricePerKwh;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocationName() {
        return locationName;
    }

    public double getEnergyKwh() {
        return energyKwh;
    }

    public double getPricePerKwh() {
        return pricePerKwh;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
