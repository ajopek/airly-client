package agh.iisg.lab.api_data_procesors;

import agh.iisg.lab.airly_api.AirlyData;

public abstract class Printer implements IApiDataProcessor {
    abstract protected String processData(AirlyData data);
    abstract protected String getHeader();

    public void apply(AirlyData data){
        System.out.println(getHeader());
        System.out.println(processData(data));
    }
}
