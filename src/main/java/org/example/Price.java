package org.example;

public class Price {

    private int priceId;
    private ChargerType chargerType;
    private double pricePerKWh;
    private double pricePerMinute;

    public Price(int priceId, ChargerType chargerType, double pricePerKWh, double pricePerMinute) {
        this.priceId = priceId;
        this.chargerType = chargerType;
        this.pricePerKWh = pricePerKWh;
        this.pricePerMinute = pricePerMinute;
    }

    public Price() {
    }



    public double calculateCost(double kWh, double minutes) {
        return kWh * pricePerKWh + minutes * pricePerMinute;
    }



    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public ChargerType getChargerType() {
        return chargerType;
    }

    public void setChargerType(ChargerType chargerType) {
        this.chargerType = chargerType;
    }

    public double getPricePerKWh() {
        return pricePerKWh;
    }

    public void setPricePerKWh(double pricePerKWh) {
        this.pricePerKWh = pricePerKWh;
    }

    public double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }
}
