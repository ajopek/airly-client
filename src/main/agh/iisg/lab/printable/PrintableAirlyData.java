package agh.iisg.lab.printable;

import agh.iisg.lab.airly_api.AirlyData;
import agh.iisg.lab.airly_api.MeasurementWithDate;
import agh.iisg.lab.airly_api.QualityMeasurment;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PrintableAirlyData {
    private PrintableMeasurement currentMeasurements;
    private LinkedList<PrintableMeasurementWithDate> forecast;
    private LinkedList<PrintableMeasurementWithDate> history;

    private void setCurrentMeasurements(QualityMeasurment currentMeasurements) {
        this.currentMeasurements = new PrintableMeasurement(currentMeasurements);
    }

    private void setForecast(List<MeasurementWithDate> forecast) {
        this.forecast = forecast.stream()
                .map(PrintableMeasurementWithDate::new)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private void setHistory(List<MeasurementWithDate> history) {
        this.history = history.stream()
                .map(PrintableMeasurementWithDate::new)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public PrintableAirlyData(AirlyData data) {
        setCurrentMeasurements(data.getCurrentMeasurements());
        setForecast(data.getForecast());
        setHistory(data.getHistory());
    }
}
