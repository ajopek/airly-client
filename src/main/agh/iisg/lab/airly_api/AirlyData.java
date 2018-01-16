package agh.iisg.lab.airly_api;

import java.util.List;

public class AirlyData {
    QualityMeasurment currentMeasurements;
    List<MeasurementWithDate> forecast;
    List<MeasurementWithDate> history;

    public void setCurrentMeasurements(QualityMeasurment currentMeasurements) {
        this.currentMeasurements = currentMeasurements;
    }

    public void setForecast(List<MeasurementWithDate> forecast) {
        this.forecast = forecast;
    }

    public void setHistory(List<MeasurementWithDate> history) {
        this.history = history;
    }

    public QualityMeasurment getCurrentMeasurements() {
        return currentMeasurements;
    }

    public List<MeasurementWithDate> getForecast() {
        return forecast;
    }

    public List<MeasurementWithDate> getHistory() {
        return history;
    }
}
