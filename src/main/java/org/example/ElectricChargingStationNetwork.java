package org.example;

//Hier habe ich alle Manager verstaut, mäßig die Controller App

class ElectricChargingStationNetwork {

    private final LocationManager locationManager;
    private final ChargerManager chargerManager;
    private final PriceManager priceManager;
    private final ClientAccountManager clientAccountManager;
    private final ChargingSessionManager chargingSessionManager;
    private final InvoiceManager invoiceManager;

    public ElectricChargingStationNetwork() {
        this.locationManager = new LocationManager();
        this.chargerManager = new ChargerManager();
        this.priceManager = new PriceManager();
        this.clientAccountManager = new ClientAccountManager();
        this.chargingSessionManager = new ChargingSessionManager();
        this.invoiceManager = new InvoiceManager();
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public ChargerManager getChargerManager() {
        return chargerManager;
    }

    public PriceManager getPriceManager() {
        return priceManager;
    }

    public ClientAccountManager getClientAccountManager() {
        return clientAccountManager;
    }

    public ChargingSessionManager getChargingSessionManager() {
        return chargingSessionManager;
    }

    public InvoiceManager getInvoiceManager() {
        return invoiceManager;
    }
}
