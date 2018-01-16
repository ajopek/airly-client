package agh.iisg.lab.printable;

import agh.iisg.lab.airly_api.MeasurementWithDate;
import agh.iisg.lab.airly_api.QualityMeasurment;

public class PrintableMeasurementWithDate {
    private String fromDateTime;
    private String tillDateTime;
    private PrintableMeasurement measurments;

    public PrintableMeasurementWithDate(MeasurementWithDate measurement) {
        this.fromDateTime = measurement.getFromDateTime();
        this.tillDateTime = measurement.getTillDateTime();
        this.measurments = new PrintableMeasurement(measurement.getMeasurments());
    }

    @Override
    public String toString() {
        return "From:" + fromDateTime +"\n"
                + "Till:" + tillDateTime +"\n"
                + measurments.toString();
    }
}
