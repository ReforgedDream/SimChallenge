package main.java.track;

import main.java.resources.StringConst;
import main.java.vehicles.Vehicle;
import main.java.vehicles.VehicleInfo;

import java.util.ArrayList;
import java.util.Objects;

public class Track {

    private final int SECONDS_IN_TEN_MINUTES = 600;
    private final int TIME_STEP = 1;
    private double distance;
    //время в секундах
    private int globalTime = 0;
    private ArrayList<Vehicle> leaders = new ArrayList<>();

    private ArrayList<Vehicle> units = new ArrayList<>();

    /**
     * Constructor
     *
     * @param distance
     */
    public Track(double distance) {
        this.distance = distance;
    }

    public void addUnit(Vehicle unit) {
        units.add(unit);
    }

    /**
     * подготовить трассу к следующему заезду
     */
    public void wipeTrack() {
        leaders.clear();
        globalTime = 0;

        for (Vehicle unit : units) {
            unit.wipeUnit();
        }
    }

    public void startRace() {

        System.out.println(String.format(StringConst.START_PLUS_DISTANCE, distance / 1000.0));

        if (!units.isEmpty()) {

            printInfo();

            while (!Objects.equals(units.size(), leaders.size())) {

                globalTime += TIME_STEP;

                for (Vehicle unit : units) {

                    //если участник не финишировал...
                    if (unit.getLapTime() == 0) {

                        //если на ремонт не надо времени...
                        if (unit.getTimeToFinishRepair() == 0) {

                            if (Math.random() <= unit.getPunctureRate()) {
                                unit.brokeTheTire();
                            }

                            //если дистанция пройдена...
                            if (unit.calculateDistance(TIME_STEP) >= distance) {
                                leaders.add(unit);
                                unit.setLapTime(globalTime);
                            }

                        } else {
                            unit.decrementTimeToFinishRepair(TIME_STEP);
                        }
                    }
                }

                if ((globalTime % SECONDS_IN_TEN_MINUTES) == 0) {
                    printSubtotal();
                }
            }

            System.out.println(StringConst.RACE_IS_OVER);

            for (Vehicle unit : leaders) {
                System.out.println(unit.toString());
            }
        } else {
            System.out.println(StringConst.NO_PARTICIPANTS);
        }
    }

    private void printInfo() {
        for (Vehicle unit : units) {
            ((VehicleInfo) unit).printInfo();
        }
        System.out.println(StringConst.RACE_BEGINS);
    }

    private void printSubtotal() {
        System.out.println(String.format(StringConst.ELAPSED_TIME, globalTime / 3600, ((globalTime % 3600) / 60), (((globalTime % 3600) % 60))));

        for (Vehicle unit : units) {
            if (unit.getDistancePassed() <= distance) {
                System.out.println(String.format(StringConst.HAS_TRAVELED, unit.getName(), unit.getDistancePassed() / 1000.0));
            }
        }
    }
}