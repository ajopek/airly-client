package agh.iisg.lab.api_data_procesors;

import agh.iisg.lab.airly_api.AirlyData;
import agh.iisg.lab.printable.PrintableMeasurement;

import java.util.Collections;

public class AsciiArtPrinter extends Printer{
    private final static String header =
            " .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
                    "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                    "| |      __      | || |     _____    | || |  _______     | || |   _____      | || |  ____  ____  | |\n" +
                    "| |     /  \\     | || |    |_   _|   | || | |_   __ \\    | || |  |_   _|     | || | |_  _||_  _| | |\n" +
                    "| |    / /\\ \\    | || |      | |     | || |   | |__) |   | || |    | |       | || |   \\ \\  / /   | |\n" +
                    "| |   / ____ \\   | || |      | |     | || |   |  __ /    | || |    | |   _   | || |    \\ \\/ /    | |\n" +
                    "| | _/ /    \\ \\_ | || |     _| |_    | || |  _| |  \\ \\_  | || |   _| |__/ |  | || |    _|  |_    | |\n" +
                    "| ||____|  |____|| || |    |_____|   | || | |____| |___| | || |  |________|  | || |   |______|   | |\n" +
                    "| |              | || |              | || |              | || |              | || |              | |\n" +
                    "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                    " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ";

    protected String getHeader() {
        return header;
    }

    protected String processData(AirlyData data){
        PrintableMeasurement printable = new PrintableMeasurement(data.getCurrentMeasurements(), AsciiArtPrinter::valueToAsciiArt);
        return printable.toString();
    }

    private static String valueToAsciiArt(Double value, Double min, Double max) {
        String representation = "+";
        if (value > max || value < min){
            representation = ":-(";
        }
        Double rounded = (value/(max - min))*100;
        Integer integer = rounded.intValue();
        integer = integer / 100;
        return String.join("", Collections.nCopies(integer, representation));
    }

}
