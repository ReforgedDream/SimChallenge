package main.java.vehicles;

import main.java.resources.GlobalConst;
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
    //время, которое осталось до конца ремонта колеса
    private int timeToFinishRepair = 0;
    //Время, за которое пройден круг
    private int lapTime = 0;
    //Имя транспортного средства
    private String name;

    public void setLapTime(int lapTime) {
        this.lapTime = lapTime;
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
        this.speed = speed / GlobalConst.MS_TO_KMPH;
        this.punctureRate = punctureRate;
        this.timeToRepair = timeToRepair;
    }

    public double getDistancePassed() {
        return this.distancePassed;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Имитировать прокол колеса
     * Выводится сообщение о проколе
     * Оставшееся время до конца ремонта загружается временем,
     * характерным для этого транспортного средства
     */
    public void brokeTheTire() {
        System.out.println(String.format(StringConst.BREAK_THE_TIRE, name));
        this.timeToFinishRepair = this.timeToRepair;
    }

    /**
     * Прогресс ремонта в зависимости от того, сколько времени прошло в симуляции
     *
     * @param time
     */
    public void decrementTimeToFinishRepair(int time) {
        this.timeToFinishRepair -= time;

        if (this.timeToFinishRepair < 0)
            this.timeToFinishRepair = 0;
    }

    //Подготовить транспортное средство к новому заезду
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

    /**
     * Для вывода таблицы победителей
     * Имя, часы, минуты и секунды
     * @return
     */
    @Override
    public String toString() {
        return String.format(StringConst.VEHICLE_TO_STRING,
                name,
                lapTime / GlobalConst.SECONDS_IN_HOUR,
                ((lapTime % GlobalConst.SECONDS_IN_HOUR) / GlobalConst.SECONDS_IN_MINUTE),
                (((lapTime % GlobalConst.SECONDS_IN_HOUR) % GlobalConst.SECONDS_IN_MINUTE)));
    }

    /**
     * Вывод информации перед заездом
     * Имя, тип, скорость и вероятность прокола колеса
     * Дополнительная информация, специфичная для типа, выводится
     * методами в соотв. классах
     * @return
     */
    public String getInfo() {
        return String.format(StringConst.INFO_VEHICLE,
                name,
                this.getClass().getSimpleName().toLowerCase(),
                speed * GlobalConst.MS_TO_KMPH,
                punctureRate);
    }
}