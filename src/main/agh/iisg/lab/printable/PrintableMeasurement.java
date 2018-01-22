package agh.iisg.lab.printable;

import agh.iisg.lab.ThreeArgumentFunction;
import agh.iisg.lab.airly_api.QualityMeasurment;

import java.net.DatagramPacket;
import java.util.function.Function;

public class PrintableMeasurement {
    private ThreeArgumentFunction<Double, Double, Double, String> valueFormater = (val, min, max) -> val.toString();
    private class MeasurementValue {
        private String name;
        private String unit;
        private Double value;
        private Double min_norm;
        private Double max_norm;

        public MeasurementValue(String name, String unit, Double min, Double max) {
            this.name = name;
            this.unit = unit;
            // Referential norm values for data visualisation
            this.max_norm = max;
            this.min_norm = min;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return name + ": " + (value == null ? "Brak danych" : valueFormater.apply(value, min_norm, max_norm) + " " + unit);
        }
    }

    private MeasurementValue pm1 = new MeasurementValue("PM1", "μg/m^3", 0.0, 30.0);
    private MeasurementValue pm10 = new MeasurementValue("PM10", "μg/m^3", 0.0, 50.0);
    private MeasurementValue pm25 = new MeasurementValue("PM2.5", "μg/m^3", 0.0, 25.0);
    private MeasurementValue pollutionLevel = new MeasurementValue("Jakość potwietrza", "(według wskaźnika CAQI)", 0.0, 100.0);
    private MeasurementValue pressure = new MeasurementValue("Ciśnienie", "hPa", 980.0, 1040.0);
    private MeasurementValue temperature = new MeasurementValue("Temperatura", "°C", -15.0, 35.0);
    private MeasurementValue humidity = new MeasurementValue("Wilgotność", "%", 0.0, 100.0);

    public PrintableMeasurement(QualityMeasurment measurment) {
        this.pm1.setValue(measurment.getPm1());
        this.pm10.setValue(measurment.getPm10());
        this.pm25.setValue(measurment.getPm25());
        this.pollutionLevel.setValue(measurment.getPollutionLevel());
        this.pressure.setValue(measurment.getPressure());
        this.temperature.setValue(measurment.getTemperature());
        this.humidity.setValue(measurment.getHumidity());
    }

    public PrintableMeasurement(QualityMeasurment measurment, ThreeArgumentFunction<Double, Double, Double, String> valueFormater) {
        this.pm1.setValue(measurment.getPm1());
        this.pm10.setValue(measurment.getPm10());
        this.pm25.setValue(measurment.getPm25());
        this.pollutionLevel.setValue(measurment.getPollutionLevel());
        this.pressure.setValue(measurment.getPressure());
        this.temperature.setValue(measurment.getTemperature());
        this.humidity.setValue(measurment.getHumidity());
        this.valueFormater = valueFormater;
    }

    @Override
    public String toString() {
        return pm1.toString() + "\n"
                + pm10.toString() + "\n"
                + pm25.toString() + "\n"
                + pollutionLevel.toString() + "\n"
                + pressure.toString() + "\n"
                + temperature.toString() +"\n"
                + humidity +"\n";
    }
}
