package org.example;

import java.util.ArrayList;
import java.util.List;

public class LocationManager {

    private List<Location> locations = new ArrayList<>();

    public Location createLocation(int id, String name, String address, String status) {
        Location location = new Location(id, name, address, status);
        locations.add(location);
        return location;
    }

    public Location findLocationById(int id) {
        for (Location location : locations) {
            if (location.getLocationId() == id) {
                return location;
            }
        }
        return null;
    }

    public List<Location> getAllLocations() {
        return locations;
    }
}
