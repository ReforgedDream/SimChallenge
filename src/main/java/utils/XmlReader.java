package main.java.utils;

import main.java.track.Track;
import main.java.vehicles.Bike;
import main.java.vehicles.Car;
import main.java.vehicles.Truck;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlReader {

    public Track readXml(String configFileName) {
        String filePath = new File("").getAbsolutePath();

        File xmlFile = new File(filePath.concat("\\" + configFileName));

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = dBuilder.parse(xmlFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element dist = (Element) doc.getElementsByTagName("track").item(0);

        Track track1 = new Track(Double.parseDouble(dist.getAttribute("distance")));

        NodeList vehiclesList = doc.getElementsByTagName("vehicle");

        for (int i = 0; i < vehiclesList.getLength(); i++) { //Чтение и запись данных по проектам

            Element element = (Element) vehiclesList.item(i);

            switch (element.getAttribute("type")) {
                case "bike":
                    track1.addUnit(new Bike(
                            element.getAttribute("name"),
                            Double.parseDouble(element.getAttribute("speed")),
                            Double.parseDouble(element.getAttribute("punctureRate")),
                            Boolean.parseBoolean(element.getAttribute("hasSidecar"))));
                    break;

                case "truck":
                    track1.addUnit(new Truck(
                            element.getAttribute("name"),
                            Double.parseDouble(element.getAttribute("speed")),
                            Double.parseDouble(element.getAttribute("punctureRate")),
                            Double.parseDouble(element.getAttribute("load"))));
                    break;

                case "car":
                    track1.addUnit(new Car(
                            element.getAttribute("name"),
                            Double.parseDouble(element.getAttribute("speed")),
                            Double.parseDouble(element.getAttribute("punctureRate")),
                            Short.parseShort(element.getAttribute("passengers"))));
                    break;

                default:
                    break;
            }

        }

        return track1;
    }
}
