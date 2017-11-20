package main.java.vehicles;

import main.java.resources.GlobalConst;
import main.java.resources.StringConst;

import java.util.InputMismatchException;

public class Truck extends Vehicle implements VehicleInfo {

    //Масса груза
    private double load;

    public Truck(String name, double speed, double punctureRate, double load) {
        super(name, speed, punctureRate, GlobalConst.TRUCK_TIME);

        if (load < 0) {
            System.out.println(StringConst.CARGO_MISMATCH);
            throw new InputMismatchException();
        }

        this.load = load;
    }

    public void printInfo() {
        System.out.println(super.getInfo() + String.format(StringConst.CARGO_WEIGHT, load));
    }
}