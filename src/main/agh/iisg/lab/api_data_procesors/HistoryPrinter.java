package agh.iisg.lab.api_data_procesors;

import agh.iisg.lab.airly_api.AirlyData;
import agh.iisg.lab.printable.PrintableMeasurementWithDate;

public class HistoryPrinter extends Printer {
    private static final String header = "Dane historyczne:";

    @Override
    public String getHeader() {
        return header;
    }

    String processData(AirlyData data) {
        return data.getHistory().stream()
                .map(PrintableMeasurementWithDate::new)
                .map(PrintableMeasurementWithDate::toString)
                .reduce((s1, s2) -> s1 + s2 +"\n")
                .orElse("Brak danych historycznych. \n");
    }
}
