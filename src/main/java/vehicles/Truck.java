package main.java.vehicles;

import main.java.resources.GlobalConst;

public class Truck extends Vehicle implements VehicleInfo {

    //Масса груза
    private double load;

    public Truck(String name, double speed, double punctureRate, double load) {
        super(name, speed, punctureRate, GlobalConst.TRUCK_TIME);
        this.load = load;
    }

    public void printInfo() {
        System.out.println(super.getInfo() + ", cargo weight: " + load);
    }
}