package main.java.vehicles;

import main.java.resources.GlobalConst;

public class Car extends Vehicle implements VehicleInfo {

    //количество пассажиров
    private short passengers;

    public Car(String name, double speed, double punctureRate, short passengers) {
        super(name, speed, punctureRate, GlobalConst.CAR_TIME);
        this.passengers = passengers;
    }

    public void printInfo() {
        System.out.println(super.getInfo() + ", passengers: " + passengers);
    }
}