package org.example;

public class ChargingSessionManager {

    public void startCharging(Charger charger) {
        charger.setStatus(ChargerStatus.OCCUPIED);
    }

    public void stopCharging(Charger charger) {
        charger.setStatus(ChargerStatus.AVAILABLE);
    }
}
