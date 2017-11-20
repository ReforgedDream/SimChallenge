package main.java.resources;

public class StringConst {

    public static final String INFO_VEHICLE = "%s, type: %s, speed: %4.0f kmph, puncture rate: %1.6f";
    public static final String VEHICLE_TO_STRING = "%s, lap time: %02d hours, %02d minutes, %02d seconds";
    public static final String BREAK_THE_TIRE = "%s has a tire broken!";
    public static final String START_PLUS_DISTANCE = "New race. Distance: %s kilometers";
    public static final String ELAPSED_TIME = "Time elapsed: %02d hours, %02d minutes, %02d seconds";
    public static final String HAS_TRAVELED = "%s has traveled %4.2f kilometers";

    public static final String CARGO_WEIGHT = ", cargo weight: %f tons";
    public static final String PASSENGERS = ", passengers: %d";
    public static final String HAS_SIDECAR = ", has a sidecar: %s";

    public static final String NO_PARTICIPANTS = "No participants";
    public static final String RACE_BEGINS = "---Race begins!---";
    public static final String RACE_IS_OVER = "---Race is over---";

    public static final String SPEED_MISMATCH = "Speed of vehicle specified in XML is less or equals zero";
    public static final String PROBABILITY_MISMATCH = "Probability of tire break specified in XML is more than 1 or less than 0";
    public static final String PASSENGERS_MISMATCH = "Number of passengers specified in XML is either negative or more than 4";
    public static final String CARGO_MISMATCH = "Cargo weight specified in XML is negative";
    public static final String DISTANCE_MISMATCH = "Length of the track specified in XML is less or equals zero";

}
