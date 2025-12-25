package org.example;

import java.util.ArrayList;
import java.util.List;

//E2 – Manage Chargers (Owner Perspective)

public class ChargerManager {

    private final List<Charger> chargers = new ArrayList<>();

    // US2.1 – Create Charger
    public Charger createCharger(int chargerId,
                                 ChargerType type,
                                 ChargerStatus status,
                                 double ignoredValue, // aktuell nicht benutzt (könnte z.B. kW-Leistung sein)
                                 Location location) {

        Charger charger = new Charger(chargerId, type, status);


        chargers.add(charger);

        // Standort setzen & in Location registrieren
        if (location != null) {
            charger.setLocation(location);
            location.addCharger(charger);
        }

        return charger;
    }

    // US2.3 – Update Charger (nur Status)
    public void updateChargerStatus(int chargerId, ChargerStatus newStatus) {
        Charger charger = findChargerById(chargerId);
        if (charger != null) {
            charger.setStatus(newStatus);
        }
    }


    public void updateCharger(Charger charger) {

    }

    // US2.4 – Delete Charger
    public void deleteCharger(int chargerId) {

        Charger charger = findChargerById(chargerId);

        if (charger != null) {
            Location loc = charger.getLocation();
            if (loc != null) {
                loc.removeCharger(charger);
            }

            chargers.remove(charger);
        }
    }

    // US2.2 – Read Charger
    public List<Charger> getChargersByLocation(Location location) {
        if (location == null) {
            return new ArrayList<>();
        }
        return location.getChargers();
    }


    public List<Charger> getAllChargers() {
        return chargers;
    }

    // Charger nach ID suchen
    public Charger findChargerById(int chargerId) {
        for (Charger c : chargers) {
            if (c.getChargerId() == chargerId) {
                return c;
            }
        }
        return null;
    }
}
