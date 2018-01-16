package agh.iisg.lab.airly_api;

public class MeasurementWithDate {
    private String fromDateTime;
    private String tillDateTime;
    private QualityMeasurment measurements;

    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public void setTillDateTime(String tillDateTime) {
        this.tillDateTime = tillDateTime;
    }

    public void setMeasurments(QualityMeasurment measurments) {
        this.measurements = measurments;
    }

    public String getFromDateTime() {
        return fromDateTime;
    }

    public String getTillDateTime() {
        return tillDateTime;
    }

    public QualityMeasurment getMeasurments() {
        return measurements;
    }
}
