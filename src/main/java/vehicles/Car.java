package main.java.vehicles;

import main.java.resources.GlobalConst;
import main.java.resources.StringConst;

import java.util.InputMismatchException;

public class Car extends Vehicle implements VehicleInfo {

    //количество пассажиров
    private short passengers;

    public Car(String name, double speed, double punctureRate, short passengers) {
        super(name, speed, punctureRate, GlobalConst.CAR_TIME);

        if (passengers < 0 || passengers > 4) {
            System.out.println(StringConst.PASSENGERS_MISMATCH);
            throw new InputMismatchException();
        }

        this.passengers = passengers;
    }

    public void printInfo() {
        System.out.println(super.getInfo() + String.format(StringConst.PASSENGERS, passengers));
    }
}