package agh.iisg.lab.api_data_procesors;

import agh.iisg.lab.airly_api.AirlyData;
import agh.iisg.lab.printable.PrintableMeasurement;

public class CurrentMeasurementPrinter extends Printer{
    private static String header = "Stan obecny:";

    @Override
    public String getHeader() {
        return header;
    }

    String processData(AirlyData data) {
        PrintableMeasurement printable = new PrintableMeasurement(data.getCurrentMeasurements());
        return printable.toString();
    }


}
