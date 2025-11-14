package org.example;

import java.util.ArrayList;
import java.util.List;

public class LocationManager {

    private List<Location> locations = new ArrayList<>();

    public Location createLocation(int locationId, String name, String address, String status) {
        Location location = new Location(locationId, name, address, status);
        locations.add(location);
        return location;
    }

    public void updateLocation(Location location) {

    }

    public void deleteLocation(int locationId) {
        locations.removeIf(location -> location.getLocationId() == locationId);
    }

    public List<Location> getAllLocations() {
        return locations;
    }
}
