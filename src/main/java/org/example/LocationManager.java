package org.example;

import java.util.ArrayList;
import java.util.List;


 // Verwalter für alle Standorte.
 // E1 – Manage Locations (Owner Perspective)

public class LocationManager {

    private final List<Location> locations = new ArrayList<>();

    // US1.1 – Create Location

    public Location createLocation(int id, String name, String address, String status) {
        Location location = new Location(id, name, address, status);
        locations.add(location);
        return location;
    }


    public void addLocation(Location location) {
        if (location != null && findLocationById(location.getLocationId()) == null) {
            locations.add(location);
        }
    }


    public Location findLocationById(int id) {
        for (Location location : locations) {
            if (location.getLocationId() == id) {
                return location;
            }
        }
        return null;
    }

    // US1.2 – Read Locations

    public List<Location> getAllLocations() {
        return locations;
    }

    // US1.3 – Update Locations

    public void updateLocation(int id, String newName, String newAddress, String newStatus) {
        Location loc = findLocationById(id);
        if (loc != null) {
            loc.setName(newName);
            loc.setAddress(newAddress);
            loc.setStatus(newStatus);
        }
    }

    // US1.4 – Delete Locations

    public void deleteLocation(int id) {
        locations.removeIf(loc -> loc.getLocationId() == id);
    }
}
