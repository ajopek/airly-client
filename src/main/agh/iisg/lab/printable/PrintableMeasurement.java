package agh.iisg.lab.printable;

import agh.iisg.lab.airly_api.QualityMeasurment;

public class PrintableMeasurement {
    private class MeasurementValue {
        private String name;
        private String unit;
        private Double value;

        public MeasurementValue(String name, String unit) {
            this.name = name;
            this.unit = unit;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return name + ": " + (value == null ? "Brak danych" : value.toString() + " " + unit);
        }
    }

    private MeasurementValue pm1 = new MeasurementValue("PM1", "μg/m^3");
    private MeasurementValue pm10 = new MeasurementValue("PM10", "μg/m^3");
    private MeasurementValue pm25 = new MeasurementValue("PM2.5", "μg/m^3");
    private MeasurementValue pollutionLevel = new MeasurementValue("Jakość potwietrza", "(według wskaźnika CAQI)");
    private MeasurementValue pressure = new MeasurementValue("Ciśnienie", "hPa");
    private MeasurementValue temperature = new MeasurementValue("Temperatura", "°C");
    private MeasurementValue humidity = new MeasurementValue("Wilgotność", "%");

    public PrintableMeasurement(QualityMeasurment measurment) {
        this.pm1.setValue(measurment.getPm1());
        this.pm10.setValue(measurment.getPm10());
        this.pm25.setValue(measurment.getPm25());
        this.pollutionLevel.setValue(measurment.getPollutionLevel());
        this.pressure.setValue(measurment.getPressure());
        this.temperature.setValue(measurment.getTemperature());
        this.humidity.setValue(measurment.getHumidity());
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
