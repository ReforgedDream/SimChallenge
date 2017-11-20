package main.java.vehicles;

import main.java.resources.StringConst;

public abstract class Vehicle {

    //скорость транспортного средства
    private double speed;
    //вероятность прокола
    private double punctureRate;
    //время, необходимое для ремонта колеса
    private int timeToRepair;
    //пройденное расстояние
    private double distancePassed = 0;
    //время, которое осталось до ремонта колеса
    private int timeToFinishRepair = 0;

    //Время, за которое пройден круг
    private int lapTime = 0;

    private String name;

    public void setLapTime(int lapTime) {
        this.lapTime = lapTime;
    }

    public void decrementTimeToFinishRepair(int time){
        this.timeToFinishRepair -= time;

        if(this.timeToFinishRepair < 0)
            this.timeToFinishRepair = 0;
    }

    public int getLapTime(){
        return this.lapTime;
    }

    public int getTimeToFinishRepair(){
        return this.timeToFinishRepair;
    }

    public double getPunctureRate(){
        return this.punctureRate;
    }

    /**
     * Constructor
     * @param speed
     * @param punctureRate
     * @param timeToRepair
     */
    public Vehicle(String name, double speed, double punctureRate, int timeToRepair){

        this.name = name;
        this.speed = speed / 3.6;
        this.punctureRate = punctureRate;
        this.timeToRepair = timeToRepair;

    }

    public double getDistancePassed() {
        return this.distancePassed;
    }

    public String getName() {
        return this.name;
    }

    public void brokeTheTire() {
        System.out.println(String.format(StringConst.BREAK_THE_TIRE, name));
        this.timeToFinishRepair = this.timeToRepair;
    }

    public void wipeUnit() {
        distancePassed = 0.0;
        timeToFinishRepair = 0;
        lapTime = 0;
    }

    /**
     *
     * @param elapsedTime интервал времени для расчета расстояния
     */
    public double calculateDistance(int elapsedTime){

        return distancePassed += speed * (double)elapsedTime;
    }

    @Override
    public String toString() {
        return String.format(StringConst.VEHICLE_TO_STRING, name, lapTime / 3600, ((lapTime % 3600) / 60), (((lapTime % 3600) % 60)));
    }

    public String getInfo() {
        return String.format(StringConst.INFO_VEHICLE, name, this.getClass().getSimpleName().toLowerCase(), speed * 3.6, punctureRate);
    }
}

