package main.java.vehicles;

import main.java.resources.GlobalConst;
import main.java.resources.StringConst;

public class Bike extends Vehicle implements VehicleInfo {

    //есть ли люлька
    private boolean hasSidecar;

    public Bike(String name, double speed, double punctureRate, boolean hasSidecar) {
        super(name, speed, punctureRate, GlobalConst.BIKE_TIME);
        this.hasSidecar = hasSidecar;
    }

    public void printInfo() {
        System.out.println(super.getInfo() + String.format(StringConst.HAS_SIDECAR, hasSidecar));
    }
}