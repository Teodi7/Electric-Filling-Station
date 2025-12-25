package org.example;


 // Wird in E2 (Manage Chargers) und E8 (Charge EV) benötigt
public class Charger {

    private int chargerId;
    private ChargerType type;
    private ChargerStatus status;
    private Location location;

    public Charger(int chargerId, ChargerType type, ChargerStatus status) {
        this.chargerId = chargerId;
        this.type = type;
        this.status = status;
    }

    public Charger() {
    }

    // US2.3 – Update Charger (damit ich Status ändern)
    public void setStatus(ChargerStatus newStatus) {
        this.status = newStatus;
    }

    // wird Location.getAvailableChargers() verwendet
    public boolean isAvailable() {
        return status == ChargerStatus.AVAILABLE;
    }

    // Alle Getter / Setter

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

    // Standort des Chargers
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
