package main.java.track;

import main.java.resources.GlobalConst;
import main.java.resources.StringConst;
import main.java.vehicles.Vehicle;
import main.java.vehicles.VehicleInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Objects;

public class Track {

    //длина трека в метрах
    private double distance;
    //время в секундах
    private int globalTime = 0;
    //Окончившие заезд участники в порядке прибытия к финишу
    private ArrayList<Vehicle> leaders = new ArrayList<>();
    //Участники гонки
    private ArrayList<Vehicle> units = new ArrayList<>();

    /**
     * Constructor
     *
     * @param distance
     */
    public Track(double distance) {
        if (distance <= 0) {
            System.out.println(StringConst.DISTANCE_MISMATCH);
            throw new InputMismatchException();
        }
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

        System.out.println(String.format(StringConst.START_PLUS_DISTANCE, distance / GlobalConst.METERS_IN_KILOMETER));

        if (!units.isEmpty()) {

            //перед стартом вывести информацию об участниках
            printInfo();

            System.out.println(StringConst.RACE_BEGINS);

            while (!Objects.equals(units.size(), leaders.size())) {

                //Инкремент симуляционного времени
                globalTime += GlobalConst.TIME_STEP;

                for (Vehicle unit : units) {

                    //если участник не финишировал...
                    if (unit.getLapTime() == 0) {

                        //если на ремонт не надо времени...
                        if (unit.getTimeToFinishRepair() <= 0) {

                            if (Math.random() < unit.getPunctureRate()) {
                                unit.brokeTheTire();
                            }

                            //если дистанция пройдена...
                            if (unit.calculateDistance(GlobalConst.TIME_STEP) >= distance) {
                                leaders.add(unit);
                                unit.setLapTime(globalTime);
                            }

                        } else {
                            //Если время на ремонт больше нуля, то уменьшить его
                            //на количество прошедшего симуляционного времени
                            unit.decrementTimeToFinishRepair(GlobalConst.TIME_STEP);
                        }
                    }
                }

                if ((globalTime % GlobalConst.SECONDS_IN_TEN_MINUTES) == 0) {
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

    /**
     * Вывод подробной информации об участниках перед стартом
     */
    private void printInfo() {
        for (Vehicle unit : units) {
            ((VehicleInfo) unit).printInfo();
        }
    }

    /**
     * Вывод промежуточного состояния участников
     * через равные интервалы симуляционного времени
     */
    private void printSubtotal() {
        System.out.println(String.format(StringConst.ELAPSED_TIME,
                globalTime / GlobalConst.SECONDS_IN_HOUR,
                ((globalTime % GlobalConst.SECONDS_IN_HOUR) / GlobalConst.SECONDS_IN_MINUTE),
                (((globalTime % GlobalConst.SECONDS_IN_HOUR) % GlobalConst.SECONDS_IN_MINUTE))));

        //Сортировка участников в порядке убывания пройденного пути
        Collections.sort(units);
        //вывод информации о тех участниках, кто еще в пути
        for (Vehicle unit : units) {
            if (unit.getDistancePassed() <= distance) {
                System.out.println(String.format(StringConst.HAS_TRAVELED,
                        unit.getName(),
                        unit.getDistancePassed() / GlobalConst.METERS_IN_KILOMETER));
            }
        }
    }
}