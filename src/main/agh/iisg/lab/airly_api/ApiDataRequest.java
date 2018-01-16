package agh.iisg.lab.airly_api;

import agh.iisg.lab.ArgType;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import agh.iisg.lab.api_data_procesors.CurrentMeasurementPrinter;
import agh.iisg.lab.api_data_procesors.ForecastPrinter;
import agh.iisg.lab.api_data_procesors.HistoryPrinter;
import agh.iisg.lab.api_data_procesors.IApiDataProcessor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiDataRequest {
    private static final String airlyApiUrl = "https://airapi.airly.eu/v1/";
    private static final int readTimeout = 5;
    private static final int connectTimeout = 5;
    private List<IApiDataProcessor> processorList;
    /*
     * Builds and executes data request based on provided args, and request type.
     * Throws IllegalArgumentException if provided RequestType handling is not implemented.
     */
    public ApiDataRequest(HashMap<ArgType, String> args, RequestType type, List<IApiDataProcessor> processorList) {
        this.processorList = processorList;
        Retrofit retrofit = buildRetrofit();
        AirlyService service = buildService(retrofit);
        Call<AirlyData> call = createCall(service, type, args);
        performCall(call);
    }

    private OkHttpClient buildHttpClient() {
        return new OkHttpClient
                .Builder()
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .build();
    }

    private Retrofit buildRetrofit() {
        return new Retrofit
                    .Builder()
                    .baseUrl(airlyApiUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(buildHttpClient())
                    .build();
    }

    private AirlyService buildService(Retrofit retrofit) {
        return retrofit.create(AirlyService.class);
    }


    /*
     * Create api call based on provided requestType.
     */
    private Call<AirlyData> createCall(AirlyService service, RequestType requestType, HashMap<ArgType, String> args) {
        Call<AirlyData> call = null;
        switch (requestType) {
            case MapPoint:
                call = service.mapPointData(args.get(ArgType.Latitiude), args.get(ArgType.Longtitiude), args.get(ArgType.ApiKey));
                break;
            case Sensor:
                call = service.sensorData(args.get(ArgType.SensorId), args.get(ArgType.ApiKey));
                break;
            default:
                throw new IllegalArgumentException("Invalid request type");
        }
        return call;
    }

    private void performCall(Call<AirlyData> call){
        call.enqueue(new Callback<AirlyData>() {
                         @Override
                         public void onResponse(Call<AirlyData> call, Response<AirlyData> response) {
                            try {
                                handleResponse(call, response);
                            } catch (IOException e) {
                                System.err.println("Getting data from Airly service failed.");
                                System.err.println(e.getMessage());
                                System.exit(1);
                            }
                         }

                         @Override
                         public void onFailure(Call call, Throwable throwable) {
                             System.err.println("Connecting with Airly service failed.");
                             System.err.println(throwable.getMessage());
                             System.exit(1);
                         }
                     }

        );
    }
    /*
     * Handles response from Airly api.
     * Sets response QualityMeasurement object or throws IOException if data request failed.
     */
    private void handleResponse(Call call, Response<AirlyData> response) throws IOException {
        // TODO Handle rest of response codes
        switch (response.code()) {
            case 403:
                throw new IOException("Forbiden");
            case 400:
                break;
            case 401:
                throw new IOException("Unauthorized");
            case 404:
                break;
            case 500:
                break;
            case 200:
                applyProcessors(response.body());
                System.exit(0);
                break;
            default:
                throw new IOException("Unknown Airly api error code " +response.code());
        }
    }

    private void applyProcessors(AirlyData data) {
        processorList.stream()
                .forEach(p -> p.apply(data));
    }


    public static void main(String[] args) {
        HashMap<ArgType, String> arguments = new HashMap<>();
        arguments.put(ArgType.ApiKey, "b2c7e93da1114e88b85eaad4e2948121");
        arguments.put(ArgType.Longtitiude, "19.94098");
        arguments.put(ArgType.Latitiude, "50.06201");
        List <IApiDataProcessor> proce = new LinkedList<>();
        proce.add(new CurrentMeasurementPrinter());
        proce.add(new ForecastPrinter());
        proce.add(new HistoryPrinter());
        ApiDataRequest request = new ApiDataRequest(arguments, RequestType.MapPoint, proce);
    }
}