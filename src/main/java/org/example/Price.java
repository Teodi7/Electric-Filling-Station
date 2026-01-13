package org.example;

import java.time.LocalDateTime;

public class Price {

    private Location location;
    private ChargerType chargerType;
    private double pricePerKwh;

    // NEU: exakte Uhrzeit
    private LocalDateTime timestamp;

    public Price(Location location, ChargerType chargerType, double pricePerKwh) {
        this.location = location;
        this.chargerType = chargerType;
        this.pricePerKwh = pricePerKwh;
        this.timestamp = LocalDateTime.now(); // ⬅️ Uhrzeit setzen
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

    //  NEU
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
