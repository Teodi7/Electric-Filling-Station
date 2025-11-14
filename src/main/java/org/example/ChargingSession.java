package org.example;

import java.time.Duration;
import java.time.LocalDateTime;

public class ChargingSession {

    private int sessionId;
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

    public double calculateCost(Price price) {
        double minutes = getDurationMinutes();
        this.totalCost = price.calculateCost(energyKWh, minutes);
        return totalCost;
    }

    private double getDurationMinutes() {
        if (startTime == null || endTime == null) {
            return 0;
        }
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMinutes();
    }


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
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
