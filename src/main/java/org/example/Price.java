package org.example;

import java.time.LocalDate;

public class Price {

    private Location location;
    private ChargerType chargerType;
    private double pricePerKwh;
    private LocalDate date;

    public Price(Location location, ChargerType chargerType, double pricePerKwh, LocalDate date) {
        this.location = location;
        this.chargerType = chargerType;
        this.pricePerKwh = pricePerKwh;
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public ChargerType getChargerType() {
        return chargerType;
    }

    public double getPricePerKwh() {
        return pricePerKwh;
    }

    public LocalDate getDate() {
        return date;
    }
}
