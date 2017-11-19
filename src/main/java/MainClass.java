package main.java;

import main.java.track.Track;
import main.java.utils.XmlReader;

import java.util.Objects;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        Track track1 = new XmlReader().readXml("settings.xml");

        track1.startRace();

        while (true) {

            System.out.println("Start the new lap? y/n");

            String inStr = in.next();

            if (Objects.equals(inStr, "y")) {
                track1.wipeTrack();
                track1.startRace();
            } else if (Objects.equals(inStr, "n")) {
                break;
            } else {
                continue;
            }
        }
    }
}
