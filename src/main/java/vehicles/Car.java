package main.java.vehicles;

public class Car extends Vehicle implements VehicleInfo {

    private static final int CAR_TIME = 600;

    //количество пассажиров
    private short passengers;

    public Car(String name, double speed, double punctureRate, short passengers) {
        super(name, speed, punctureRate, CAR_TIME);
        this.passengers = passengers;
    }

    public void printInfo() {
        System.out.println(super.getInfo() + ", passengers: " + passengers);
    }
}
