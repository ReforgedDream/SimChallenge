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
        //файл настроек должен храниться в директории проекта (исполняемого файла)
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

        //Выбрать из файла первый элемент с тэгом "track"
        Element dist = (Element) doc.getElementsByTagName("track").item(0);
        //Создать трек с длиной, равной атрибуту "distance" этого элемента
        Track track1 = new Track(Double.parseDouble(dist.getAttribute("distance")));
        //Выбрать все элементы с тэгом "vehicle"
        NodeList vehiclesList = doc.getElementsByTagName("vehicle");

        //последовательно обойти все элементы "vehicle"
        //и создать на треке транспортные средства в соотв. с атрибутами этих элементов
        for (int i = 0; i < vehiclesList.getLength(); i++) {

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
