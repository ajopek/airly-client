package agh.iisg.lab.api_data_procesors;

import agh.iisg.lab.airly_api.AirlyData;

public interface IApiDataProcessor {
    void apply(AirlyData data);
}
