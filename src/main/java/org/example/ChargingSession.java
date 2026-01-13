package org.example;

public class ChargingSession {

    private Charger charger;
    private double energyKwh;

    public ChargingSession(Charger charger) {
        this.charger = charger;
        this.energyKwh = 0.0;
    }

    public Charger getCharger() {
        return charger;
    }

    public double getEnergyKwh() {
        return energyKwh;
    }

    public void setEnergyKwh(double energyKwh) {
        this.energyKwh = energyKwh;
    }
}
