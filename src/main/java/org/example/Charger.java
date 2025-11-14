package org.example;

public class Charger {

    private int chargerId;
    private ChargerType type;
    private ChargerStatus status;

    public Charger(int chargerId, ChargerType type, ChargerStatus status) {
        this.chargerId = chargerId;
        this.type = type;
        this.status = status;

    }

    public Charger() {
    }



    public void setStatus(ChargerStatus newStatus) {
        this.status = newStatus;
    }

    public boolean isAvailable() {
        return status == ChargerStatus.AVAILABLE;
    }



    public int getChargerId() {
        return chargerId;
    }

    public void setChargerId(int chargerId) {
        this.chargerId = chargerId;
    }

    public ChargerType getType() {
        return type;
    }

    public void setType(ChargerType type) {
        this.type = type;
    }

    public ChargerStatus getStatus() {
        return status;
    }




}
