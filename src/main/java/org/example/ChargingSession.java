package org.example;

import java.time.Duration;
import java.time.LocalDateTime;


//Wird für Kostenberechnung (US8.1) und für Rechnungen (E9) verwendet.

public class ChargingSession {

    private int sessionId;
    private ClientAccount account;
    private Charger charger;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double energyKWh;
    private double totalCost;

    public ChargingSession(int sessionId) {
        this.sessionId = sessionId;
    }

    public ChargingSession() {
    }


    public void startSession() {
        this.startTime = LocalDateTime.now();
    }

    public void endSession() {
        this.endTime = LocalDateTime.now();
    }


    public void setEndTimeNow() {
        this.endTime = LocalDateTime.now();
    }

    // US8.1 – Charge EV
    public double calculateCost(Price price) {
        if (price == null) {
            return 0.0;
        }
        double minutes = getDurationMinutes();
        this.totalCost = price.calculateCost(energyKWh, minutes);
        return totalCost;
    }


    private double getDurationMinutes() {
        if (startTime == null || endTime == null) {
            return 0.0;
        }
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMinutes();
    }

    //  Getter / Setter

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public ClientAccount getAccount() {
        return account;
    }

    public void setAccount(ClientAccount account) {
        this.account = account;
    }

    public Charger getCharger() {
        return charger;
    }

    public void setCharger(Charger charger) {
        this.charger = charger;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public double getEnergyKWh() {
        return energyKWh;
    }

    public void setEnergyKWh(double energyKWh) {
        this.energyKWh = energyKWh;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
