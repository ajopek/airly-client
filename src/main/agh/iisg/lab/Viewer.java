package agh.iisg.lab;

import agh.iisg.lab.airly_api.ApiDataRequest;
import agh.iisg.lab.airly_api.RequestType;
import agh.iisg.lab.api_data_procesors.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Viewer {
    private HashMap<ArgType, String> executionOptions = new HashMap<>();
    private List<IApiDataProcessor> processors = new LinkedList<>();

    public Viewer(HashMap<ArgType, Optional<String>> parsedArgs) {
                parsedArgs.entrySet().stream()
                    .filter(entry -> entry.getValue().isPresent())
                        //all null optionals are filtered, so get is safe
                    .forEach(entry -> executionOptions.put(entry.getKey(), entry.getValue().get()));
    }

    private void putApiKey() {
        String apiKey = System.getenv("API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            if (!executionOptions.containsKey(ArgType.ApiKey)) {
                throw new IllegalArgumentException("Specify api key in API_KEY system variable, or as argument." +
                                                   "\n For more information use --help.");
            }
        } else {
            executionOptions.put(ArgType.ApiKey, apiKey);
        }
    }

    private RequestType getRequestType() {
        RequestType type = null;
        if (executionOptions.containsKey(ArgType.Latitude) && executionOptions.containsKey(ArgType.Longitude)) {
            type = RequestType.MapPoint;
        }
        if (executionOptions.containsKey(ArgType.SensorId)) {
            type = RequestType.Sensor;
        }
        if (type == null) {
            throw new IllegalArgumentException("Please specify latitiude and longtitude or sensor id");
        }
        return type;
    }

    private void addDataProcessors() {
        processors.add(new CurrentMeasurementPrinter());
        if (executionOptions.containsKey(ArgType.History)) {
            processors.add(new HistoryPrinter());
        }
    }

    public void execute() {
        try {
            putApiKey();
            RequestType requestType = getRequestType();
            addDataProcessors();
            new ApiDataRequest(executionOptions, requestType, processors);
        } catch (IllegalArgumentException e) {
            System.err.println("There were errors while executing your request:");
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }
}
