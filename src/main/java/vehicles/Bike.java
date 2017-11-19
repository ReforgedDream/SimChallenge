package main.java.vehicles;

public class Bike extends Vehicle implements VehicleInfo {

    private static final int BIKE_TIME = 300;

    //есть ли люлька
    private boolean hasSidecar;

    public Bike(String name, double speed, double punctureRate, boolean hasSidecar) {
        super(name, speed, punctureRate, BIKE_TIME);
        this.hasSidecar = hasSidecar;
    }

    public void printInfo() {
        System.out.println(super.getInfo() + ", has a sidecar: " + hasSidecar);
    }
}
