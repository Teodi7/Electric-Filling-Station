package org.example;


 //Preis für einen bestimmten Standort und einen bestimmten Charger-Typ.

public class Price {

    private Location location;
    private ChargerType chargerType;
    private double pricePerKwh;
    private double pricePerMinute;   // Preis pro Minute

    // Preis pro kWh und pro Minute
    public Price(Location location, ChargerType chargerType,
                 double pricePerKwh, double pricePerMinute) {
        this.location = location;
        this.chargerType = chargerType;
        this.pricePerKwh = pricePerKwh;
        this.pricePerMinute = pricePerMinute;
    }

    // Minutenpreis = 0
    public Price(Location location, ChargerType chargerType, double pricePerKwh) {
        this(location, chargerType, pricePerKwh, 0.0);
    }

    // US8.1 – Charge EV
    // Kosten für einen Ladevorgang
    public double calculateCost(double energyKWh, double minutes) {
        double costEnergy = energyKWh * pricePerKwh;
        double costTime = minutes * pricePerMinute;
        return costEnergy + costTime;
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

    public double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerKwh(double pricePerKwh) {
        this.pricePerKwh = pricePerKwh;
    }

    public void setPricePerMinute(double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }
}
