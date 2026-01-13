package org.example;

import java.time.LocalDateTime;

public class Invoice {

    private String locationName;
    private double energyKwh;
    private double pricePerKwh;
    private double totalAmount;


    private ChargerType chargerType;
    private LocalDateTime timestamp;

    public Invoice(
            String locationName,
            double energyKwh,
            double pricePerKwh,
            ChargerType chargerType
    ) {
        this.locationName = locationName;
        this.energyKwh = energyKwh;
        this.pricePerKwh = pricePerKwh;
        this.chargerType = chargerType;
        this.timestamp = LocalDateTime.now(); // ⬅️ exakte Uhrzeit
        this.totalAmount = energyKwh * pricePerKwh;
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

    //neu
    public ChargerType getChargerType() {
        return chargerType;
    }

    // NEU
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
