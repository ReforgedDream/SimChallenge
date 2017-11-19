package main.java.vehicles;

public class Truck extends Vehicle implements VehicleInfo {

    private static final int TRUCK_TIME = 1800;

    //Масса груза
    private double load;

    public Truck(String name, double speed, double punctureRate, double load) {
        super(name, speed, punctureRate, TRUCK_TIME);
        this.load = load;
    }

    public void printInfo() {
        System.out.println(super.getInfo() + ", cargo weight: " + load);
    }
}
