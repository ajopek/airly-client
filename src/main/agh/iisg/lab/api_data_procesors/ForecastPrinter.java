package agh.iisg.lab.api_data_procesors;

import agh.iisg.lab.airly_api.AirlyData;
import agh.iisg.lab.printable.PrintableMeasurement;
import agh.iisg.lab.printable.PrintableMeasurementWithDate;

public class ForecastPrinter extends Printer {
    private static final String header = "Prognoza:";

    public String getHeader() {
        return header;
    }

    String processData(AirlyData data) {
        return data.getForecast().stream()
                .map(PrintableMeasurementWithDate::new)
                .map(PrintableMeasurementWithDate::toString)
                .reduce((s1, s2) -> s1 + "\n" +s2 + "\n")
                .orElse("Brak prognozy \n");
    }
}
