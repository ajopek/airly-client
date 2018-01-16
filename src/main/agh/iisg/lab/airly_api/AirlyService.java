package agh.iisg.lab.airly_api;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AirlyService {
    @GET("mapPoint/measurements")
    Call<AirlyData> mapPointData(
            @Query("latitude") String latitude,
            @Query("longitude") String longitude,
            @Query("apikey") String apiKey
    );

    @GET("sensor/measurements")
    Call<AirlyData> sensorData(
            @Query("sensorId") String sensorId,
            @Query("apikey") String apiKey
    );
}
