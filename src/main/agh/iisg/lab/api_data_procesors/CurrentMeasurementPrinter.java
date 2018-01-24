package agh.iisg.lab.api_data_procesors;

import agh.iisg.lab.airly_api.AirlyData;
import agh.iisg.lab.printable.PrintableMeasurement;

public class CurrentMeasurementPrinter extends Printer{
    private static String header = " .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
            "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
            "| |      __      | || |     _____    | || |  _______     | || |   _____      | || |  ____  ____  | |\n" +
            "| |     /  \\     | || |    |_   _|   | || | |_   __ \\    | || |  |_   _|     | || | |_  _||_  _| | |\n" +
            "| |    / /\\ \\    | || |      | |     | || |   | |__) |   | || |    | |       | || |   \\ \\  / /   | |\n" +
            "| |   / ____ \\   | || |      | |     | || |   |  __ /    | || |    | |   _   | || |    \\ \\/ /    | |\n" +
            "| | _/ /    \\ \\_ | || |     _| |_    | || |  _| |  \\ \\_  | || |   _| |__/ |  | || |    _|  |_    | |\n" +
            "| ||____|  |____|| || |    |_____|   | || | |____| |___| | || |  |________|  | || |   |______|   | |\n" +
            "| |              | || |              | || |              | || |              | || |              | |\n" +
            "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n" +
            "Stan obecny:";

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    protected String processData(AirlyData data) {
        PrintableMeasurement printable = new PrintableMeasurement(data.getCurrentMeasurements());
        return printable.toString();
    }


}
